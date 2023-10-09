package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.service.OrderService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class ShiftCompletionDateTime implements IAction {
    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id ");
        long orderId = scanner.nextInt();
        System.out.println("Enter year ");
        int year = scanner.nextInt();
        System.out.println("Enter month ");
        int month = scanner.nextInt();
        System.out.println("Enter day of month ");
        int dayOfMonth = scanner.nextInt();
        System.out.println("Enter hour ");
        int hour = scanner.nextInt();

        Order order = OrderService.getInstance().getOrderById(orderId);
        order.setCompletionDate(LocalDateTime.of(year, month, dayOfMonth, hour, 0));
    }
}