package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

public class ImportOrdersFromJson implements IAction {

    private final static Logger logger = Logger.getLogger(ImportOrdersFromJson.class);

    private OrderService orderService;

    public ImportOrdersFromJson(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void execute() {

        System.out.println("Import import orders from json");
        try {
            orderService
                    .importOrdersFromJson();
        } catch (Exception exception) {
            logger.error("Can't import orders from json", exception);
        }
    }
}
