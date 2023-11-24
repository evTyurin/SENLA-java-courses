package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class ShiftStartDateTime implements IAction {

    private final static Logger logger = Logger.getLogger(ShiftStartDateTime.class);

    @Override
    public void execute() {

        try {
            Order order = OrderService.getInstance().getOrderById(1);
            order.setStartDate(LocalDateTime.of(2023, 10, 15, 10, 0));
        } catch (Exception exception) {
            logger.error("Can't shift start date time", exception);
        }
      }
}
