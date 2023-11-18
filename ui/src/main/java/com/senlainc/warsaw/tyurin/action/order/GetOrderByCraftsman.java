package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class GetOrderByCraftsman implements IAction {

    private final static Logger logger = Logger.getLogger(GetOrderByCraftsman.class);

    @Override
    public void execute() throws Exception {

        try {
            System.out.println(OrderService
                    .getInstance()
                    .getOrderByCraftsmen(1).toString());
        } catch (Exception exception) {
            logger.error("Can't egt order by craftsman", exception);
        }
    }
}