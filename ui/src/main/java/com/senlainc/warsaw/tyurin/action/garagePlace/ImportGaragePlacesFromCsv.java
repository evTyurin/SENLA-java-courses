package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class ImportGaragePlacesFromCsv implements IAction {

    @Override
    public void execute() {

        System.out.println("Import from csv");
        GaragePlaceService
                .getInstance()
                .importGaragePlacesFromCsv();
    }
}
