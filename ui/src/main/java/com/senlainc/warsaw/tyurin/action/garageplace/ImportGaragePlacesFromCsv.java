package com.senlainc.warsaw.tyurin.action.garageplace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class ImportGaragePlacesFromCsv implements IAction {

    private final static Logger logger = Logger.getLogger(ImportGaragePlacesFromCsv.class);

    @Override
    public void execute() {

        try {
            System.out.println("Import garage places from csv");
            GaragePlaceService
                    .getInstance()
                    .importGaragePlacesFromCsv();
        } catch (Exception exception) {
            logger.error("Can't import garage places from csv", exception);
        }
    }
}
