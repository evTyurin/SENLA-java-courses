package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class GetOrderById implements IAction {

    private final static Logger logger = Logger.getLogger(GetOrderById.class);

    public OrderService orderService;

    public GetOrderById(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        try {
            System.out.println(orderService.getOrderById(1));
        } catch (Exception exception) {
            System.out.println(exception);
            logger.error("Can't get order by id", exception);
        }
    }
}