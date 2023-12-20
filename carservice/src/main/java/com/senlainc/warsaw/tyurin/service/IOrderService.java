package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.exception.ExpectationFailedException;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface IOrderService {

    void changeStatus(long id, OrderStatus status) throws NotFoundException;

    void shiftStartDateTime(long id, LocalDateTime startDateTime) throws NotFoundException;

    void shiftCompletionDateTime(long id, LocalDateTime completionDateTime) throws NotFoundException;

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
                      long garagePlaceId) throws NotFoundException;

    void addOrder(Order order) throws NotFoundException;

    Order getOrderById(long id) throws NotFoundException;

    void removeOrder(Long id) throws NotFoundException;

    List<Order> getArchivedOrdersSortByCriteria(String criteria) throws ExpectationFailedException;

    List<Order> getCurrentlyExecutedOrdersSortedByCriteria(String criteria) throws ExpectationFailedException;

    List<Order> getSortedAllOrdersByCriteria(String criteria) throws ExpectationFailedException;

    void updateOrder(Order order) throws NotFoundException;
}
