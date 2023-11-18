package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class ExportGaragePlacesToCsv implements IAction {

    private final static Logger logger = Logger.getLogger(ExportGaragePlacesToCsv.class);

    @Override
    public void execute() throws Exception {

        System.out.println("Export garage place to csv");
        try {
            GaragePlaceService
                    .getInstance()
                    .exportGaragePlacesToCsv();
        } catch (Exception exception) {
            logger.error("Can't export garage place to csv", exception);
        }
    }
}
