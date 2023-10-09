package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

import java.util.Scanner;

public class GetOrderByCraftsman implements IAction {
    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter craftsman id ");
        long craftsmanId = scanner.nextLong();
        System.out.println(OrderService
                .getInstance()
                .getOrderByCraftsmen(craftsmanId).toString());
    }
}