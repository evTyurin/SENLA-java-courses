package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class ImportGaragePlacesFromJson implements IAction {

    private final static Logger logger = Logger.getLogger(ImportGaragePlacesFromJson.class);

    @Override
    public void execute() {

        System.out.println("Import garage places from json");
        try {
            GaragePlaceService
                    .getInstance()
                    .importGaragePlacesFromJson();
        } catch (Exception exception) {
            logger.error("Can't import garage places from json", exception);
        }
    }
}
