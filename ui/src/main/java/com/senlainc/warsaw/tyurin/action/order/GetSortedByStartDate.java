package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class GetSortedByStartDate implements IAction {

    private final static Logger logger = Logger.getLogger(GetSortedByStartDate.class);

    @Override
    public void execute() {

        System.out.println("List of orders sorted by start date");
        try {
            OrderService
                    .getInstance()
                    .getSortedByStartDate()
                    .forEach(order -> System.out.println(order.toString()));
        } catch (Exception exception) {
            logger.error("Can't get list of orders sorted by start date", exception);
        }
    }
}