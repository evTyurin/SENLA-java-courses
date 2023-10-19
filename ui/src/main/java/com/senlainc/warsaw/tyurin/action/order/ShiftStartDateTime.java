package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.service.OrderService;

import java.time.LocalDateTime;

public class ShiftStartDateTime implements IAction {
    @Override
    public void execute() {

        Order order = OrderService.getInstance().getOrderById(1);
        order.setStartDate(LocalDateTime.of(2023, 10, 15, 10, 0));
    }
}
