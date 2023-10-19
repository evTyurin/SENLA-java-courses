package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetSortedByCompletionDate implements IAction {
    @Override
    public void execute() {

        System.out.println("List of orders sorted by completion date");

        OrderService
                .getInstance()
                .getSortedByCompletionDate()
                .forEach(order -> System.out.println(order.toString()));
    }
}
