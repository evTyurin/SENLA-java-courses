package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.exception.ExpectationFailedException;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;

import java.util.List;

public interface IOrderService {

    Order getOrderByCraftsmen(long craftsmanId);

    List<Order> getOrders();

    void addOrder(Order order) throws NotFoundException;

    Order getOrderById(long id) throws NotFoundException;

    void removeOrder(Long id) throws NotFoundException;

    List<Order> getArchivedOrdersSortByCriteria(String criteria) throws ExpectationFailedException;

    List<Order> getCurrentlyExecutedOrdersSortedByCriteria(String criteria) throws ExpectationFailedException;

    List<Order> getSortedAllOrdersByCriteria(String criteria) throws ExpectationFailedException;

    void updateOrder(Order order) throws NotFoundException;
}
