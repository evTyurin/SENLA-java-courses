package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetOrderByCraftsman implements IAction {
    @Override
    public void execute() throws Exception {

        System.out.println(OrderService
                .getInstance()
                .getOrderByCraftsmen(1).toString());
    }
}