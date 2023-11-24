package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class GetArchivedOrdersSortedByCompletionDate implements IAction {

    private final static Logger logger = Logger.getLogger(GetArchivedOrdersSortedByCompletionDate.class);

    @Override
    public void execute() {

        System.out.println("List of archived orders sorted by completion date");
        try {
            OrderService
                    .getInstance()
                    .getArchivedOrdersSortedByCompletionDate()
                    .forEach(order -> System.out.println(order.toString()));
        } catch (Exception exception) {
            logger.error("Can't get list of archived orders sorted by completion date", exception);
        }
    }
}