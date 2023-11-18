package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class ExportOrdersToCsv implements IAction {

    private final static Logger logger = Logger.getLogger(ExportOrdersToCsv.class);

    @Override
    public void execute() throws Exception {

        try {
            System.out.println("Export to csv");
            OrderService
                    .getInstance()
                    .exportOrdersToCsv();
        } catch (Exception exception) {
            logger.error("Can't export order to csv", exception);
        }
    }
}
