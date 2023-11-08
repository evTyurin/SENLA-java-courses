package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.service.OrderService;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

public class ChangeOrderStatus implements IAction {
    @Override
    public void execute() throws Exception {

        Order order = OrderService.getInstance().getOrderById(1);
        order.setOrderStatus(OrderStatus.COMPLETED);
    }
}