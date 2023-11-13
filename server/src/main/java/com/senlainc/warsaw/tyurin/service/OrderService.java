package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.annotation.ConfigProperty;
import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.dao.IOrderDAO;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.OrderStatus;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvWriter;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonReader;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonWriter;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DependencyClass
public class OrderService implements IOrderService{

    private final static Logger logger = Logger.getLogger(OrderService.class);

    private static OrderService INSTANCE;
    @DependencyComponent
    private IOrderDAO orderDAO;
    @DependencyComponent
    private CsvReader csvReader;
    @DependencyComponent
    private CsvWriter csvWriter;
    @DependencyComponent
    private JsonReader jsonReader;
    @DependencyComponent
    private JsonWriter jsonWriter;

    @ConfigProperty(propertyKey = Constants.ABILITY_TO_SHIFT_ORDER_COMPLETION_TIME)
    private boolean isCompletionDateTimeShiftable;
    @ConfigProperty(propertyKey = Constants.ABILITY_TO_REMOVE_ORDER)
    private boolean isOrderRemovable;

    public static OrderService getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void changeStatus(long id, OrderStatus status) throws Exception {
        orderDAO.getOrder(id).setOrderStatus(status);
    }

    @Override
    public void shiftStartDateTime(long id, LocalDateTime startDateTime) throws Exception {
        orderDAO.getOrder(id).setStartDate(startDateTime);
    }

    @Override
    public void shiftCompletionDateTime(long id, LocalDateTime completionDateTime) throws Exception {
        if (isCompletionDateTimeShiftable) {
            orderDAO.getOrder(id).setCompletionDate(completionDateTime);
        } else {
            System.out.println("Shifting completion time was prohibited");
            logger.error("Shifting completion time was prohibited");
        }
    }

    @Override
    public List<Order> getSortedBySubmissionDate() throws Exception {
        return orderDAO.getOrdersSubmissionDateSorted();
    }

    @Override
    public List<Order> getSortedByCompletionDate() throws Exception {
        return orderDAO.getOrdersCompletionDateSorted();
    }

    @Override
    public List<Order> getSortedByStartDate() throws Exception {
        return orderDAO.getOrdersStartDateSorted();
    }

    @Override
    public List<Order> getSortedByPrice() throws Exception {
        return orderDAO.getOrdersPriceSorted();
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedBySubmissionDate() throws Exception {
        return orderDAO.getInProgressOrdersSubmissionDateSorted();
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedByCompletionDate() throws Exception {
        return orderDAO.getInProgressOrdersCompletionDateSorted();
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedByPrice() throws Exception {
        return orderDAO.getInProgressOrdersStartDateSorted();
    }

    @Override
    public List<Order> getArchivedOrdersSortedBySubmissionDate() throws Exception {
        return orderDAO.getArchivedOrdersSubmissionDateSorted();
    }

    @Override
    public List<Order> getArchivedOrdersSortedByCompletionDate() throws Exception {
        return orderDAO.getArchivedOrdersCompletionDateSorted();
    }

    @Override
    public List<Order> getArchivedOrdersSortedByPrice() throws Exception {
        return orderDAO.getArchivedOrdersPriceSorted();
    }

    @Override
    public Order getOrderByCraftsmen(long craftsmanId) throws Exception {
        return orderDAO.getOrderByCraftsmen(craftsmanId);
    }

    @Override
    public List<Order> getOrders() throws Exception {
        return orderDAO.getOrders();
    }

    @Override
    public Order createOrder(double price,
                             LocalDateTime startDate,
                             LocalDateTime completionDate,
                             List<Long> craftsmenId,
                             long garagePlaceId) {
        Order order = new Order();
        order.setPrice(price);
        order.setStartDate(startDate);
        order.setCompletionDate(completionDate);
        order.setCraftsmen(craftsmenId);
        order.setGaragePlaceId(garagePlaceId);
        return order;
    }

    @Override
    public void addOrder(Order order) throws Exception {
        orderDAO.addOrder(order);
    }

    @Override
    public Order getOrderById(long id) throws Exception {
        return orderDAO.getOrder(id);
    }

    @Override
    public void importOrdersFromCsv() {

        csvReader
                .readEntities(Constants.PATH_TO_ORDERS_CSV)
                .stream()
                .map(entity -> {
                    String[] values = entity.split(",");
                    Order order = new Order();
                    order.setId(Long.parseLong(values[0]));
                    order.setPrice(Double.parseDouble(values[1]));
                    order.setSubmissionDate(LocalDateTime.parse(values[2]));
                    order.setStartDate(LocalDateTime.parse(values[3]));
                    order.setCompletionDate(LocalDateTime.parse(values[4]));
                    switch (values[5]) {
                        case "COMPLETED":
                            order.setOrderStatus(OrderStatus.COMPLETED);
                            break;
                        case "CANCELED":
                            order.setOrderStatus(OrderStatus.CANCELED);
                            break;
                        case "NEW":
                            order.setOrderStatus(OrderStatus.NEW);
                            break;
                        case "IN_PROGRESS":
                            order.setOrderStatus(OrderStatus.IN_PROGRESS);
                            break;
                        case "DELETED":
                            order.setOrderStatus(OrderStatus.DELETED);
                            break;
                    }
                    String[] craftsmanIdList = values[6].split(";");
                    Arrays.stream(craftsmanIdList).forEach(id -> {
                        order.getCraftsmenId().add(Long.parseLong(id));
                    });
                    order.setGaragePlaceId(Long.parseLong(values[7]));
                    return order;
                })
                .forEach(importOrder -> {
                    Order order = null;
                    try {
                        order = getOrderById(importOrder.getId());
                    } catch (Exception exception) {
                        logger.error("Can't get order", exception);
                    }
                    if (order == null) {
                        try {
                            orderDAO.addOrder(importOrder);
                        } catch (Exception exception) {
                            logger.error("Can't add order", exception);
                        }
                    } else if (!order.equals(importOrder)) {
                        order.setOrderStatus(importOrder.getOrderStatus());
                        order.setStartDate(importOrder.getStartDate());
                        order.setCompletionDate(importOrder.getCompletionDate());
                        order.setSubmissionDate(importOrder.getSubmissionDate());
                        order.setPrice(importOrder.getPrice());
                        order.setGaragePlaceId(importOrder.getGaragePlaceId());
                        order.setCraftsmen(importOrder.getCraftsmenId());
                    }
                });
    }

    @Override
    public void exportOrdersToCsv() throws Exception {

        List<String> orders = orderDAO
                .getOrders()
                .stream()
                .sorted(Comparator.comparing(Order::getId))
                .map(Order::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(orders,
                Constants.ORDERS_CSV_HEADER,
                Constants.PATH_TO_ORDERS_CSV);
    }

    @Override
    public void removeOrder(Long id) throws Exception {

        if (isOrderRemovable) {
            orderDAO.deleteOrder(id);
        } else {
            System.out.println("Removing order was prohibited");
            logger.error("Removing order was prohibited");
        }
    }

    @Override
    public void importOrdersFromJson() {

        jsonReader
                .readEntities(Order.class, Constants.PATH_TO_ORDERS_JSON)
                .forEach(importOrder -> {
                    Order order = null;
                    try {
                        order = getOrderById(importOrder.getId());
                    } catch (Exception exception) {
                        logger.error("Can't get order", exception);
                    }
                    if (order == null) {
                        try {
                            orderDAO.addOrder(importOrder);
                        } catch (Exception exception) {
                            logger.error("Can't add order", exception);
                        }
                    } else if (!order.equals(importOrder)) {
                        order.setOrderStatus(importOrder.getOrderStatus());
                        order.setStartDate(importOrder.getStartDate());
                        order.setCompletionDate(importOrder.getCompletionDate());
                        order.setSubmissionDate(importOrder.getSubmissionDate());
                        order.setPrice(importOrder.getPrice());
                        order.setGaragePlaceId(importOrder.getGaragePlaceId());
                        order.setCraftsmen(importOrder.getCraftsmenId());
                    }
                });
    }

    @Override
    public void exportOrdersToJson() throws Exception {
        jsonWriter.writeEntities(orderDAO.getOrders(), Constants.PATH_TO_ORDERS_JSON);
    }

    @Override
    public List<Order> getNotCanceledOrders() throws Exception {
        return orderDAO.getNotCanceledOrders();
    }

    @Override
    public List<Order> getInProgressOrders() throws Exception {
        return orderDAO.getInProgressOrders();
    }
}