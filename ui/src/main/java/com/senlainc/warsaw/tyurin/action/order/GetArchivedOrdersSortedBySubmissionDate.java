package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetArchivedOrdersSortedBySubmissionDate implements IAction {

    @Override
    public void execute() throws Exception {

        System.out.println("List of archived orders sorted by completion date");

        OrderService
                .getInstance()
                .getArchivedOrdersSortedByCompletionDate()
                .forEach(order -> System.out.println(order.toString()));
    }
}