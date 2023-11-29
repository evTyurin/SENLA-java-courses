package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class RemoveOrder implements IAction {

    private final static Logger logger = Logger.getLogger(RemoveOrder.class);

    private OrderService orderService;

    public RemoveOrder(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        try {
            orderService.removeOrder(1L);
        } catch (Exception exception) {
            logger.error("Can't remove order", exception);
        }
    }
}
