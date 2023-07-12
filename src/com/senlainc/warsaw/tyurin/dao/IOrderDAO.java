package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Order;

import java.util.List;

public interface IOrderDAO {

    void addOrder(Order order);

    void deleteOrder(Order order);

    List<Order> getOrders();
}
