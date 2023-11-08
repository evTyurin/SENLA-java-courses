package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetCurrentlyExecutedOrdersSortedBySubmissionDate implements IAction {
    @Override
    public void execute() throws Exception {

        System.out.println("List of currently executed orders sorted by submission date");

        OrderService
                .getInstance()
                .getCurrentlyExecutedOrdersSortedBySubmissionDate()
                .forEach(order -> System.out.println(order.toString()));
    }
}