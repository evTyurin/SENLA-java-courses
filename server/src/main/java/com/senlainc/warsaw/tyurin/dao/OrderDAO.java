package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.entity.Order;

import java.util.ArrayList;
import java.util.List;

@DependencyClass
public class OrderDAO implements IOrderDAO{

    private static OrderDAO INSTANCE;
    private List<Order> orders;

    public OrderDAO() {
        orders = new ArrayList<>();
    }

    public static OrderDAO getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void deleteOrder(Order order) {
        orders.remove(order);
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }
}