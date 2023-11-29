package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class GetSortedByPrice implements IAction {

    private final static Logger logger = Logger.getLogger(GetSortedByPrice.class);

    private OrderService orderService;

    public GetSortedByPrice(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        System.out.println("List of orders sorted by price");
        try {
            orderService
                    .getSortedByPrice()
                    .forEach(order -> System.out.println(order.toString()));
        } catch (Exception exception) {
            logger.error("Can't get list of orders sorted by price", exception);
        }
    }
}