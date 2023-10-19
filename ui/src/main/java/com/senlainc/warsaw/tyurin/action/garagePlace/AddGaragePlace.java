package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class AddGaragePlace implements IAction {

    @Override
    public void execute() {

        GaragePlaceService
                .getInstance()
                .addGaragePlace(GaragePlaceService
                        .getInstance()
                        .createGaragePlace(11, 11, 15));

        GaragePlaceService
                .getInstance()
                .addGaragePlace(GaragePlaceService
                        .getInstance()
                        .createGaragePlace(15, 15, 25));

        GaragePlaceService
                .getInstance()
                .addGaragePlace(GaragePlaceService
                        .getInstance()
                        .createGaragePlace(7, 7, 15));
    }
}