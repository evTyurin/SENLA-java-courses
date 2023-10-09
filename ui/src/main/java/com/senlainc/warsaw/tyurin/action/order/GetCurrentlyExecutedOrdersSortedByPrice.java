package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetCurrentlyExecutedOrdersSortedByPrice implements IAction {
    @Override
    public void execute() {

        System.out.println("List of currently executed orders sorted by price");

        OrderService
                .getInstance()
                .getCurrentlyExecutedOrdersSortedByPrice()
                .forEach(order -> System.out.println(order.toString()));
    }
}