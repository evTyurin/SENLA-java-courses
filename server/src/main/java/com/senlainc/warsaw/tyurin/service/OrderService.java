package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.annotation.ConfigProperty;
import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;
import com.senlainc.warsaw.tyurin.dao.IOrderDao;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.OrderStatus;
import com.senlainc.warsaw.tyurin.util.csvhandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.csvhandlers.CsvWriter;
import com.senlainc.warsaw.tyurin.util.jsonhandlers.JsonReader;
import com.senlainc.warsaw.tyurin.util.jsonhandlers.JsonWriter;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DependencyClass
public class OrderService implements IOrderService{

    private final static Logger logger = Logger.getLogger(OrderService.class);

    @DependencyComponent
    private IOrderDao orderDao;
    @DependencyComponent
    private CsvReader csvReader;
    @DependencyComponent
    private CsvWriter csvWriter;
    @DependencyComponent
    private JsonReader jsonReader;
    @DependencyComponent
    private JsonWriter jsonWriter;
    @DependencyComponent
    private ICraftsmanService craftsmanService;
    @DependencyComponent
    private IGaragePlaceService garagePlaceService;
    @ConfigProperty(propertyKey = Constants.ABILITY_TO_SHIFT_ORDER_COMPLETION_TIME)
    private boolean isCompletionDateTimeShiftable;
    @ConfigProperty(propertyKey = Constants.ABILITY_TO_REMOVE_ORDER)
    private boolean isOrderRemovable;

    @Override
    public void changeStatus(long id, OrderStatus status) {
        Order order = orderDao.findById(id);
        order.setOrderStatus(status);
        orderDao.update(order);
    }

    @Override
    public void shiftStartDateTime(long id, LocalDateTime startDateTime) {
        Order order = orderDao.findById(id);
        order.setStartDate(startDateTime);
        orderDao.update(order);
    }

    @Override
    public void shiftCompletionDateTime(long id, LocalDateTime completionDateTime) {
        if (isCompletionDateTimeShiftable) {
            Order order = orderDao.findById(id);
            order.setCompletionDate(completionDateTime);
            orderDao.update(order);
        } else {
            logger.error("Shifting completion time was prohibited");
        }
    }

    @Override
    public List<Order> getSortedBySubmissionDate() {
        return orderDao.getOrdersSubmissionDateSorted();
    }

    @Override
    public List<Order> getSortedByCompletionDate() {
        return orderDao.getOrdersCompletionDateSorted();
    }

    @Override
    public List<Order> getSortedByStartDate() {
        return orderDao.getOrdersStartDateSorted();
    }

    @Override
    public List<Order> getSortedByPrice() {
        return orderDao.getOrdersPriceSorted();
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedBySubmissionDate() {
        return orderDao.getInProgressOrdersSubmissionDateSorted();
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedByCompletionDate() {
        return orderDao.getInProgressOrdersCompletionDateSorted();
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedByPrice() {
        return orderDao.getInProgressOrdersStartDateSorted();
    }

    @Override
    public List<Order> getArchivedOrdersSortedBySubmissionDate() {
        return orderDao.getArchivedOrdersSubmissionDateSorted();
    }

    @Override
    public List<Order> getArchivedOrdersSortedByCompletionDate() {
        return orderDao.getArchivedOrdersCompletionDateSorted();
    }

    @Override
    public List<Order> getArchivedOrdersSortedByPrice() {
        return orderDao.getArchivedOrdersPriceSorted();
    }

    @Override
    public Order getOrderByCraftsmen(long craftsmanId) {
        return orderDao.getOrderByCraftsmen(craftsmanId);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getAll();
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
        order.setCraftsmen(craftsmenId
                .stream()
                .map(craftsmanId -> craftsmanService
                        .getCraftsmanById(craftsmanId))
                .collect(Collectors.toList()));
        try {
            order.setGaragePlace(garagePlaceService.getGaragePlaceById(garagePlaceId));        } catch (Exception exception) {
            logger.error("Can't get garage place", exception);
        }
        return order;
    }

    @Override
    public void addOrder(Order order) {
        orderDao.create(order);
    }

    @Override
    public Order getOrderById(long id) {
        return orderDao.findById(id);
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
                        order.getCraftsmen().add(craftsmanService.getCraftsmanById(Long.parseLong(id)));
                    });
                    try {
                        order.setGaragePlace(garagePlaceService.getGaragePlaceById(Long.parseLong(values[7])));
                    } catch (Exception exception) {
                        logger.error("Can't get garage place", exception);
                    }
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
                            orderDao.create(importOrder);
                        } catch (Exception exception) {
                            logger.error("Can't add order", exception);
                        }
                    } else if (!order.equals(importOrder)) {
                        order.setOrderStatus(importOrder.getOrderStatus());
                        order.setStartDate(importOrder.getStartDate());
                        order.setCompletionDate(importOrder.getCompletionDate());
                        order.setSubmissionDate(importOrder.getSubmissionDate());
                        order.setPrice(importOrder.getPrice());
                        order.setGaragePlace(importOrder.getGaragePlace());
                        order.setCraftsmen(importOrder.getCraftsmen());
                    }
                });
    }

    @Override
    public void exportOrdersToCsv() {

        List<String> orders = orderDao
                .getAll()
                .stream()
                .sorted(Comparator.comparing(Order::getId))
                .map(Order::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(orders,
                Constants.ORDERS_CSV_HEADER,
                Constants.PATH_TO_ORDERS_CSV);
    }

    @Override
    public void removeOrder(Long id) {

        if (isOrderRemovable) {
            orderDao.delete(orderDao.findById(id));
        } else {
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
                            orderDao.create(importOrder);
                        } catch (Exception exception) {
                            logger.error("Can't add order", exception);
                        }
                    } else if (!order.equals(importOrder)) {
                        order.setOrderStatus(importOrder.getOrderStatus());
                        order.setStartDate(importOrder.getStartDate());
                        order.setCompletionDate(importOrder.getCompletionDate());
                        order.setSubmissionDate(importOrder.getSubmissionDate());
                        order.setPrice(importOrder.getPrice());
                        order.setGaragePlace(importOrder.getGaragePlace());
                        order.setCraftsmen(importOrder.getCraftsmen());
                    }
                });
    }

    @Override
    public void exportOrdersToJson() {
        jsonWriter.writeEntities(orderDao.getAll(), Constants.PATH_TO_ORDERS_JSON);
    }
}