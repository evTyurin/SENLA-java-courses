package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class AddOrder implements IAction {

    private final static Logger logger = Logger.getLogger(AddOrder.class);

    @Override
    public void execute() {

        try {
            OrderService
                    .getInstance()
                    .createOrder(100,
                            LocalDateTime.of(2023, 10, 10, 12, 0),
                            LocalDateTime.of(2023, 10, 20, 10, 0),
                            new ArrayList(Arrays. asList(1, 2)),
                            1);
        } catch (Exception exception) {
            logger.error("Can't add order", exception);
        }
    }
}
