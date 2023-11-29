package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class GetCurrentlyExecutedOrdersSortedByCompletionDate implements IAction {

    private final static Logger logger = Logger.getLogger(GetCurrentlyExecutedOrdersSortedByCompletionDate.class);

    private OrderService orderService;

    public GetCurrentlyExecutedOrdersSortedByCompletionDate(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        System.out.println("List of currently executed orders sorted by completion date");
        try {
            orderService
                    .getCurrentlyExecutedOrdersSortedByCompletionDate()
                    .forEach(order -> System.out.println(order.toString()));
        } catch (Exception exception) {
            logger.error("Can't get list of currently executed orders sorted by completion date", exception);
        }
    }
}