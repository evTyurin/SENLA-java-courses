package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.service.OrderService;
import com.senlainc.warsaw.tyurin.util.OrderStatus;
import org.apache.log4j.Logger;

public class ChangeOrderStatus implements IAction {

    private final static Logger logger = Logger.getLogger(ChangeOrderStatus.class);

    private OrderService orderService;

    public ChangeOrderStatus(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        try {
            Order order = orderService.getOrderById(1);
            order.setOrderStatus(OrderStatus.COMPLETED);
        } catch (Exception exception) {
            logger.error("Can't change order status", exception);
        }
    }
}