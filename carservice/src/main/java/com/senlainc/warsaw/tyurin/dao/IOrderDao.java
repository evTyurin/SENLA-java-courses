package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;

import java.util.List;

public interface IOrderDao {

    void create(Order object) throws NotFoundException;

    Order findById(long id) throws NotFoundException;

    void update(Order order);

    void delete(Order object);

    List<Order> getAll();

    List<Order> getOrdersPriceSorted();

    List<Order> getOrdersSubmissionDateSorted();

    List<Order> getOrdersCompletionDateSorted();

    List<Order> getOrdersStartDateSorted();

    List<Order> getInProgressOrdersSubmissionDateSorted();

    List<Order> getInProgressOrdersCompletionDateSorted();

    List<Order> getInProgressOrdersStartDateSorted();

    List<Order> getArchivedOrdersSubmissionDateSorted();

    List<Order> getArchivedOrdersCompletionDateSorted();

    List<Order> getArchivedOrdersPriceSorted();

    Order getOrderByCraftsmen(long craftsmanId);
}
