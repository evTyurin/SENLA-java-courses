package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {

    void changeStatus(Order order, OrderStatus status);

    void shiftStartDateTime(Order order, LocalDateTime startDateTime);

    void shiftCompletionDateTime(Order order, LocalDateTime completionDateTime);

    List<Order> getSortedBySubmissionDate();

    List<Order> getSortedByCompletionDate();

    List<Order> getSortedByStartDate();

    List<Order> getSortedByPrice();

    List<Order> getCurrentlyExecutedOrdersSortedBySubmissionDate();

    List<Order> getCurrentlyExecutedOrdersSortedByCompletionDate();

    List<Order> getCurrentlyExecutedOrdersSortedByPrice();

    List<Order> getArchivedOrdersSortedBySubmissionDate();

    List<Order> getArchivedOrdersSortedByCompletionDate();

    List<Order> getArchivedOrdersSortedByPrice();

    Order getOrderByCraftsmen(long craftsmanId);

    List<Order> getOrders();

    Order createOrder(long id,
                      double price,
                      LocalDateTime startDate,
                      LocalDateTime completionDate,
                      List<Long> craftsmenId,
                      long garagePlaceId);

    void addOrder(Order order);

    Order getOrderById(long id);

    void importOrders();

    void exportOrders();
}
