package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class RemoveOrder implements IAction {

    @Override
    public void execute() {

        OrderService.getInstance().removeOrder(1L);
    }
}
