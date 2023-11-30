package com.senlainc.warsaw.tyurin.action.garageplace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class ExportGaragePlacesToJson implements IAction {

    private final static Logger logger = Logger.getLogger(ExportGaragePlacesToJson.class);

    private GaragePlaceService garagePlaceService;

    public ExportGaragePlacesToJson(GaragePlaceService garagePlaceService) {
        this.garagePlaceService = garagePlaceService;
    }

    @Override
    public void execute() {

        System.out.println("Export garage place to json");
        try {
            garagePlaceService.exportGaragePlacesToJson();
        } catch (Exception exception) {
            logger.error("Can't export garage place to json", exception);
        }
    }
}
