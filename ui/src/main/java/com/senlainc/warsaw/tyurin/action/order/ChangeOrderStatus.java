package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.service.OrderService;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

import java.util.Scanner;

public class ChangeOrderStatus implements IAction {
    @Override
    public void execute() {

        System.out.println("Choose status:");
        System.out.println("1 - COMPLETED");
        System.out.println("2 - CANCELED");
        System.out.println("3 - NEW");
        System.out.println("4 - IN_PROGRESS");
        System.out.println("5 - DELETED");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id ");
        long orderId = scanner.nextLong();
        System.out.println("Choose status:");
        System.out.println("1 - COMPLETED");
        System.out.println("2 - CANCELED");
        System.out.println("3 - NEW");
        System.out.println("4 - IN_PROGRESS");
        System.out.println("5 - DELETED");
        int statusNumber = scanner.nextInt();
        Order order = OrderService.getInstance().getOrderById(orderId);

        switch (statusNumber) {
            case (1):
                order.setOrderStatus(OrderStatus.COMPLETED);
                break;
            case (2):
                order.setOrderStatus(OrderStatus.CANCELED);
                break;
            case (3):
                order.setOrderStatus(OrderStatus.NEW);
                break;
            case (4):
                order.setOrderStatus(OrderStatus.IN_PROGRESS);
                break;
            case (5):
                order.setOrderStatus(OrderStatus.DELETED);
                break;
        }

    }
}