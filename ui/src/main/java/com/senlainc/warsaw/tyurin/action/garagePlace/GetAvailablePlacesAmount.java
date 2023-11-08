package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

import java.time.LocalDateTime;

public class GetAvailablePlacesAmount implements IAction {

    @Override
    public void execute() throws Exception {

        System.out.println("Amount of available places = " + GaragePlaceService
                .getInstance()
                .getAvailablePlacesAmount(LocalDateTime.of(2023, 10, 15, 13, 0)));
    }
}