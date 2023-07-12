package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements IOrderDAO{

    private static OrderDAO INSTANCE;
    private List<Order> orders;

    private OrderDAO() {
        orders = new ArrayList<>();
    }

    public static OrderDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderDAO();
        }
        return INSTANCE;
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