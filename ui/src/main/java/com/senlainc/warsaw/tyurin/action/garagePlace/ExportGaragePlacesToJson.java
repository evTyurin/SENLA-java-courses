package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class ExportGaragePlacesToJson implements IAction {

    private final static Logger logger = Logger.getLogger(ExportGaragePlacesToJson.class);

    @Override
    public void execute() throws Exception {

        System.out.println("Export garage place to json");
        try {
            GaragePlaceService
                    .getInstance()
                    .exportGaragePlacesToJson();
        } catch (Exception exception) {
            logger.error("Can't export garage place to json", exception);
        }
    }
}
