package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Order;

import java.util.List;

public interface IOrderDAO {

    void addOrder(Order order);

    void deleteOrder(Order order);

    List<Order> getOrders();

    List<Order> importOrdersFromCsv(String path);

    void exportOrdersToCsv(List<Order> orders, String path);

    List<Order> importOrdersFromJson(String path);

    void exportOrdersToJson(List<Order> orders, String path);
}
