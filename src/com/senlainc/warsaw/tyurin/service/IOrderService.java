package com.senlainc.warsaw.tyurin.service;


import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.entity.OrderStatus;

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

    Order getOrderByCraftsmen(Craftsman craftsman);
}
