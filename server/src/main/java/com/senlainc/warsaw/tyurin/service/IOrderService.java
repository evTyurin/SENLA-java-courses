package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {

    void changeStatus(long id, OrderStatus status) throws Exception;

    void shiftStartDateTime(long id, LocalDateTime startDateTime) throws Exception;

    void shiftCompletionDateTime(long id, LocalDateTime completionDateTime) throws Exception;

    List<Order> getSortedBySubmissionDate() throws Exception;

    List<Order> getSortedByCompletionDate() throws Exception;

    List<Order> getSortedByStartDate() throws Exception;

    List<Order> getSortedByPrice() throws Exception;

    List<Order> getCurrentlyExecutedOrdersSortedBySubmissionDate() throws Exception;

    List<Order> getCurrentlyExecutedOrdersSortedByCompletionDate() throws Exception;

    List<Order> getCurrentlyExecutedOrdersSortedByPrice() throws Exception;

    List<Order> getArchivedOrdersSortedBySubmissionDate() throws Exception;

    List<Order> getArchivedOrdersSortedByCompletionDate() throws Exception;

    List<Order> getArchivedOrdersSortedByPrice() throws Exception;

    Order getOrderByCraftsmen(long craftsmanId) throws Exception;

    List<Order> getOrders() throws Exception;

    Order createOrder(double price,
                      LocalDateTime startDate,
                      LocalDateTime completionDate,
                      List<Long> craftsmenId,
                      long garagePlaceId);

    void addOrder(Order order) throws Exception;

    Order getOrderById(long id) throws Exception;

    void importOrdersFromCsv();

    void exportOrdersToCsv() throws Exception;

    void removeOrder(Long id) throws Exception;

    void importOrdersFromJson();

    void exportOrdersToJson() throws Exception;

    List<Order> getNotCanceledOrders() throws Exception;

    List<Order> getInProgressOrders() throws Exception;
}
