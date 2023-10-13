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
//import com.senlainc.warsaw.tyurin.util.propertyHandlers.PropertyReader;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DependencyClass
public class OrderService implements IOrderService{

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
    public void changeStatus(Order order, OrderStatus status) {
        for (Order storageOrder : orderDAO.getOrders()) {
            if (storageOrder.getId() == order.getId()) {
                storageOrder.setOrderStatus(status);
                break;
            }
        }
    }

    @Override
    public void shiftStartDateTime(Order order, LocalDateTime startDateTime) {
        order.setStartDate(startDateTime);
    }

    @Override
    public void shiftCompletionDateTime(Order order, LocalDateTime completionDateTime) {
        if (isCompletionDateTimeShiftable) {
            order.setCompletionDate(completionDateTime);
        } else {
            System.out.println("Shifting completion time was prohibited");
        }
    }

    @Override
    public List<Order> getSortedBySubmissionDate() {
        return orderDAO
                .getOrders()
                .stream()
                .sorted(Comparator
                        .comparing(Order::getSubmissionDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getSortedByCompletionDate() {
        return orderDAO
                .getOrders()
                .stream()
                .sorted(Comparator
                        .comparing(Order::getCompletionDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getSortedByStartDate() {
        return orderDAO
                .getOrders()
                .stream()
                .sorted(Comparator
                        .comparing(Order::getStartDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getSortedByPrice() {
        return orderDAO
                .getOrders()
                .stream()
                .sorted(Comparator
                        .comparing(Order::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedBySubmissionDate() {
        List<Order> currentlyExecutedOrders = getCurrentlyExecutedOrders();

        if (currentlyExecutedOrders.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return currentlyExecutedOrders
                .stream()
                .sorted(Comparator.comparing(Order::getSubmissionDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedByCompletionDate() {
        List<Order> currentlyExecutedOrders = getCurrentlyExecutedOrders();

        if (currentlyExecutedOrders.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return currentlyExecutedOrders
                .stream()
                .sorted(Comparator.comparing(Order::getCompletionDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedByPrice() {
        List<Order> currentlyExecutedOrders = getCurrentlyExecutedOrders();

        if (currentlyExecutedOrders.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return currentlyExecutedOrders
                .stream()
                .sorted(Comparator.comparing(Order::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getArchivedOrdersSortedBySubmissionDate() {
        List<Order> archivedOrders = getArchivedOrders();
        if (archivedOrders.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return archivedOrders
                .stream()
                .sorted(Comparator.comparing(Order::getSubmissionDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getArchivedOrdersSortedByCompletionDate() {
        List<Order> archivedOrders = getArchivedOrders();

        if (archivedOrders.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return archivedOrders
                .stream()
                .sorted(Comparator.comparing(Order::getCompletionDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getArchivedOrdersSortedByPrice() {
        List<Order> archivedOrders = getArchivedOrders();

        if (archivedOrders.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        return archivedOrders
                .stream()
                .sorted(Comparator.comparing(Order::getPrice))
                .collect(Collectors.toList());
    }

    @Override
    public Order getOrderByCraftsmen(long craftsmanId) {
        Optional<Order> optionalOrder = orderDAO
                .getOrders()
                .stream()
                .filter(order -> (order
                        .getCraftsmenId()
                        .contains(craftsmanId) && order
                        .getOrderStatus()
                        .equals(OrderStatus.IN_PROGRESS)))
                .findFirst();

        return optionalOrder.orElse(null);
    }

    @Override
    public List<Order> getOrders() {
        return orderDAO.getOrders();
    }

    @Override
    public Order createOrder(long id,
                             double price,
                             LocalDateTime startDate,
                             LocalDateTime completionDate,
                             List<Long> craftsmenId,
                             long garagePlaceId) {
        Order order = new Order();
        order.setId(id);
        order.setPrice(price);
        order.setStartDate(startDate);
        order.setCompletionDate(completionDate);
        order.setCraftsmen(craftsmenId);
        order.setGaragePlaceId(garagePlaceId);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public Order getOrderById(long id) {

        return orderDAO
                .getOrders()
                .stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElse(null);
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
                    Order order = getOrderById(importOrder.getId());
                    if (order == null) {
                        orderDAO.addOrder(importOrder);
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
    public void exportOrdersToCsv() {

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
    public void removeOrder(Long id) {

        if (isOrderRemovable) {
            orderDAO
                    .getOrders()
                    .remove(orderDAO
                            .getOrders()
                            .stream()
                            .filter(order -> order.getId() == id)
                            .findFirst()
                            .orElse(null));
        } else {
            System.out.println("Removing order was prohibited");
        }
    }

    @Override
    public void importOrdersFromJson() {

        jsonReader
                .readEntities(Order.class, Constants.PATH_TO_ORDERS_JSON)
                .forEach(importOrder -> {
                    Order order = getOrderById(importOrder.getId());
                    if (order == null) {
                        orderDAO.addOrder(importOrder);
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
    public void exportOrdersToJson() {
        jsonWriter.writeEntities(orderDAO.getOrders(), Constants.PATH_TO_ORDERS_JSON);
    }

    private List<Order> getArchivedOrders() {
        return orderDAO
                .getOrders()
                .stream()
                .filter(order -> ((!order
                        .getOrderStatus()
                        .equals(OrderStatus.NEW)) && (!order
                        .getOrderStatus()
                        .equals(OrderStatus.IN_PROGRESS))))
                .collect(Collectors.toList());
    }

    private List<Order> getCurrentlyExecutedOrders() {
        return orderDAO
                .getOrders()
                .stream()
                .filter(order -> order
                        .getOrderStatus()
                        .equals(OrderStatus.IN_PROGRESS))
                .collect(Collectors.toList());
    }
}