package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class ExportOrdersToJson implements IAction {

    private final static Logger logger = Logger.getLogger(ExportOrdersToJson.class);

    @Override
    public void execute() throws Exception {

        try {
            System.out.println("Export to json");
            OrderService
                    .getInstance()
                    .exportOrdersToJson();
        } catch (Exception exception) {
            logger.error("Can't export order to json", exception);
        }
    }
}
