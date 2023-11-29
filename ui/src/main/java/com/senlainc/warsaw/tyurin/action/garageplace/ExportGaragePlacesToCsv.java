package com.senlainc.warsaw.tyurin.action.garageplace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class ExportGaragePlacesToCsv implements IAction {

    private final static Logger logger = Logger.getLogger(ExportGaragePlacesToCsv.class);

    private GaragePlaceService garagePlaceService;

    public ExportGaragePlacesToCsv(GaragePlaceService garagePlaceService) {
        this.garagePlaceService = garagePlaceService;
    }

    @Override
    public void execute() {

        System.out.println("Export garage place to csv");
        try {
            garagePlaceService
                    .exportGaragePlacesToCsv();
        } catch (Exception exception) {
            logger.error("Can't export garage place to csv", exception);
        }
    }
}
