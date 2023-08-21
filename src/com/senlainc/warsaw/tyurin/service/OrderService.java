package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.IOrderDAO;
import com.senlainc.warsaw.tyurin.dao.OrderDAO;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService implements IOrderService{
    private static OrderService INSTANCE;
    private IOrderDAO orderDAO;

    private OrderService() {
        this.orderDAO = OrderDAO.getInstance();
    }

    public static OrderService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderService();
        }
        return INSTANCE;
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
        order.setCompletionDate(completionDateTime);
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
    public void importOrders() {

        orderDAO
                .importOrders(Constants.PATH_TO_ORDERS_CSV)
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
    public void exportOrders() {

        List<Order> orders = orderDAO
                .getOrders()
                .stream()
                .sorted(Comparator.comparing(Order::getId))
                .collect(Collectors.toList());

        orderDAO.exportOrders(orders, Constants.PATH_TO_ORDERS_CSV);
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