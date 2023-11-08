package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.service.OrderService;

import java.time.LocalDateTime;

public class ShiftCompletionDateTime implements IAction {
    @Override
    public void execute() throws Exception {

        Order order = OrderService.getInstance().getOrderById(1);
        order.setCompletionDate(LocalDateTime.of(2023, 10, 10, 12, 0));
    }
}