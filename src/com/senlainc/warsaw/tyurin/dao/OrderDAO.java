package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.fileHandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.fileHandlers.CsvWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO implements IOrderDAO{

    private static OrderDAO INSTANCE;
    private List<Order> orders;
    private CsvReader cvsReader;
    private CsvWriter csvWriter;

    private OrderDAO() {
        orders = new ArrayList<>();
        cvsReader = CsvReader.getInstance();
        csvWriter = CsvWriter.getInstance();
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

    @Override
    public List<String> importOrders(String path) {
        return cvsReader.readEntities(path);
    }

    @Override
    public void exportOrders(List<Order> orders, String path) {

        List<String> rawOrders = orders
                .stream()
                .map(Order::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(rawOrders, path);
    }
}