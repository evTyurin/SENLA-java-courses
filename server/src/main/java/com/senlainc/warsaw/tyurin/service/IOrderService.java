package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {

    void changeStatus(long id, OrderStatus status);

    void shiftStartDateTime(long id, LocalDateTime startDateTime);

    void shiftCompletionDateTime(long id, LocalDateTime completionDateTime);

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

    Order createOrder(double price,
                      LocalDateTime startDate,
                      LocalDateTime completionDate,
                      List<Long> craftsmenId,
                      long garagePlaceId);

    void addOrder(Order order);

    Order getOrderById(long id);

    void importOrdersFromCsv();

    void exportOrdersToCsv();

    void removeOrder(Long id);

    void importOrdersFromJson();

    void exportOrdersToJson();
}
