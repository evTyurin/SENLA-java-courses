package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class ExportGaragePlacesToJson implements IAction {

    @Override
    public void execute() throws Exception {

        System.out.println("Export to json");
        GaragePlaceService
                .getInstance()
                .exportGaragePlacesToJson();
    }
}
