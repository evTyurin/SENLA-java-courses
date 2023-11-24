package com.senlainc.warsaw.tyurin.action.garageplace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class GetAvailablePlacesAmount implements IAction {

    private final static Logger logger = Logger.getLogger(GetAvailablePlacesAmount.class);

    @Override
    public void execute() {

        try {
            System.out.println("Amount of available places = " + GaragePlaceService
                    .getInstance()
                    .getAvailablePlacesAmount(LocalDateTime.of(2023, 10, 15, 13, 0)));
        } catch (Exception exception) {
            logger.error("Can't get amount of available places", exception);
        }
    }
}