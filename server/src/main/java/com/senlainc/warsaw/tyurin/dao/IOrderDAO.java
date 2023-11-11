package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Order;

import java.util.List;

public interface IOrderDAO {

    void addOrder(Order order) throws Exception;

    void deleteOrder(long id) throws Exception;

    List<Order> getOrders() throws Exception;

    List<Order> getOrdersPriceSorted() throws Exception;

    List<Order> getOrdersSubmissionDateSorted() throws Exception;

    List<Order> getOrdersCompletionDateSorted() throws Exception;

    List<Order> getOrdersStartDateSorted() throws Exception;

    Order getOrder(long id) throws Exception;

    List<Order> getNotCanceledOrders() throws Exception;

    List<Order> getInProgressOrders() throws Exception;

    List<Order> getInProgressOrdersSubmissionDateSorted() throws Exception;

    List<Order> getInProgressOrdersCompletionDateSorted() throws Exception;

    List<Order> getInProgressOrdersStartDateSorted() throws Exception;

    List<Order> getArchivedOrdersSubmissionDateSorted() throws Exception;

    List<Order> getArchivedOrdersCompletionDateSorted() throws Exception;

    List<Order> getArchivedOrdersPriceSorted() throws Exception;

    Order getOrderByCraftsmen(long craftsmanId) throws Exception;

    Long getOrderStatusId(String orderStatus) throws Exception;
}
