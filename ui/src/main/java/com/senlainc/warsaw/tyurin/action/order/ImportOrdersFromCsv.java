package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class ImportOrdersFromCsv implements IAction {

    private final static Logger logger = Logger.getLogger(ImportOrdersFromCsv.class);

    private OrderService orderService;

    public ImportOrdersFromCsv(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        System.out.println("Import orders from csv");
        try {
            orderService
                    .importOrdersFromCsv();
        } catch (Exception exception) {
            logger.error("Can't import orders from csv", exception);
        }
    }
}
