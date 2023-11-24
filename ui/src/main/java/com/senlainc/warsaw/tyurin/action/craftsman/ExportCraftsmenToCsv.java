package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class ExportCraftsmenToCsv implements IAction {

    private final static Logger logger = Logger.getLogger(ExportCraftsmenToCsv.class);

    @Override
    public void execute() {

        System.out.println("Export to csv");
        try {
            CraftsmanService
                    .getInstance()
                    .exportCraftsmenToCsv();
        } catch (Exception exception) {
            logger.error("Can't export craftsman to csv", exception);
        }
    }
}
