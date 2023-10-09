package com.senlainc.warsaw.tyurin.action.order;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class ExportOrdersToCsv implements IAction {

    @Override
    public void execute() {

        System.out.println("Export to csv");
        OrderService
                .getInstance()
                .exportOrdersToCsv();
    }
}
