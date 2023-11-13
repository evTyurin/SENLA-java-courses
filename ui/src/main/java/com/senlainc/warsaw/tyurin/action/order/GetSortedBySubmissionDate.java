package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class GetSortedBySubmissionDate implements IAction {

    private final static Logger logger = Logger.getLogger(GetSortedBySubmissionDate.class);

    @Override
    public void execute() throws Exception {

        System.out.println("List of orders sorted by submission date");
        try {
            OrderService
                    .getInstance()
                    .getSortedBySubmissionDate()
                    .forEach(order -> System.out.println(order.toString()));
        } catch (Exception exception) {
            logger.error("Can't get list of orders sorted by submission date", exception);
        }
    }
}