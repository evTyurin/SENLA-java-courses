package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.IOrderDAO;
import com.senlainc.warsaw.tyurin.dao.OrderDAO;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.Order;
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
    public Order createOrder(String data) {
        Order order = new Order();
        String[] keyValuePairs = data.split(",");
        Arrays.stream(keyValuePairs).forEach(keyValue -> {
            if (keyValue.startsWith("id")) {
                order.setId(Long.parseLong(keyValue.substring(keyValue.indexOf(":") + 1)));
            } else if (keyValue.startsWith("price")) {
                order.setPrice(Double.parseDouble(keyValue.substring(keyValue.indexOf(":") + 1)));
            } else if (keyValue.startsWith("submissionDate")) {
                order.setSubmissionDate(LocalDateTime.parse(keyValue.substring(keyValue.indexOf(":") + 1)));
            } else if (keyValue.startsWith("startDate")) {
                order.setStartDate(LocalDateTime.parse(keyValue.substring(keyValue.indexOf(":") + 1)));
            } else if (keyValue.startsWith("completionDate")) {
                order.setCompletionDate(LocalDateTime.parse(keyValue.substring(keyValue.indexOf(":") + 1)));
            } else if (keyValue.startsWith("orderStatus")) {
                if (keyValue.endsWith("COMPLETED")) {
                    order.setOrderStatus(OrderStatus.COMPLETED);
                } else if (keyValue.endsWith("CANCELED")) {
                    order.setOrderStatus(OrderStatus.CANCELED);
                } else if (keyValue.endsWith("NEW")) {
                    order.setOrderStatus(OrderStatus.NEW);
                } else if (keyValue.endsWith("IN_PROGRESS")) {
                    order.setOrderStatus(OrderStatus.IN_PROGRESS);
                } else if (keyValue.endsWith("DELETED")) {
                    order.setOrderStatus(OrderStatus.DELETED);
                }
            } else if (keyValue.startsWith("craftsmenId")) {
                String[] craftsmanIdList = keyValue.substring(keyValue.indexOf(":") + 1).split(";");
                Arrays.stream(craftsmanIdList).forEach(id -> {
                    order.getCraftsmenId().add(Long.parseLong(id));
                });
            } else if (keyValue.startsWith("garagePlaceId")) {
                order.setGaragePlaceId(Long.parseLong(keyValue.substring(keyValue.indexOf(":") + 1)));
            }
        });
        return order;
    }

    @Override
    public void addOrder(Order order) {
        orderDAO.addOrder(order);
    }

    @Override
    public Order getOrderById(long id) {
        List<Order> orders = orderDAO.getOrders();

        for (Order order : orders) {
            if(order.getId() == id) {
                return order;
            }
        }
        return null;
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