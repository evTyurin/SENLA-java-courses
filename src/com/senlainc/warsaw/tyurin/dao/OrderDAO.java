package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.OrderStatus;
import com.senlainc.warsaw.tyurin.util.fileHandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.fileHandlers.CsvWriter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDAO implements IOrderDAO{

    private static OrderDAO INSTANCE;
    private List<Order> orders;
    private CsvReader csvReader;
    private CsvWriter csvWriter;

    private OrderDAO() {
        orders = new ArrayList<>();
        csvReader = CsvReader.getInstance();
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
    public List<Order> importOrders(String path) {

        return csvReader
                .readEntities(path)
                .stream()
                .map(entity -> {
                    String[] values = entity.split(",");
                    Order order = new Order();
                    order.setId(Long.parseLong(values[0]));
                    order.setPrice(Double.parseDouble(values[1]));
                    order.setSubmissionDate(LocalDateTime.parse(values[2]));
                    order.setStartDate(LocalDateTime.parse(values[3]));
                    order.setCompletionDate(LocalDateTime.parse(values[4]));
                    switch (values[5]) {
                        case "COMPLETED":
                            order.setOrderStatus(OrderStatus.COMPLETED);
                            break;
                        case "CANCELED":
                            order.setOrderStatus(OrderStatus.CANCELED);
                            break;
                        case "NEW":
                            order.setOrderStatus(OrderStatus.NEW);
                            break;
                        case "IN_PROGRESS":
                            order.setOrderStatus(OrderStatus.IN_PROGRESS);
                            break;
                        case "DELETED":
                            order.setOrderStatus(OrderStatus.DELETED);
                            break;
                    }
                    String[] craftsmanIdList = values[6].split(";");
                    Arrays.stream(craftsmanIdList).forEach(id -> {
                        order.getCraftsmenId().add(Long.parseLong(id));
                    });
                    order.setGaragePlaceId(Long.parseLong(values[7]));
                    return order;
                }).collect(Collectors.toList());
    }

    @Override
    public void exportOrders(List<Order> orders, String path) {

        List<String> rawOrders = orders
                .stream()
                .map(Order::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(rawOrders, Constants.ORDERS_CSV_HEADER, path);
    }
}