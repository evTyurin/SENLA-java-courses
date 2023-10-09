package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class GetNearestAvailableDate implements IAction {

    @Override
    public void execute() {

        System.out.println("Nearest available date = " + GaragePlaceService.getInstance().getNearestAvailableDate());

    }
}