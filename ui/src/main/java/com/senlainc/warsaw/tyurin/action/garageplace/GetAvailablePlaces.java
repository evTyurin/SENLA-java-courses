package com.senlainc.warsaw.tyurin.action.garageplace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class GetAvailablePlaces implements IAction {

    private final static Logger logger = Logger.getLogger(GetAvailablePlaces.class);

    private GaragePlaceService garagePlaceService;

    public GetAvailablePlaces(GaragePlaceService garagePlaceService) {
        this.garagePlaceService = garagePlaceService;
    }

    @Override
    public void execute() {

        System.out.println("List of available garage places");
        try {
            garagePlaceService
                    .getAvailablePlaces()
                    .forEach(garagePlace -> System.out.println(garagePlace.toString()));
        } catch (Exception exception) {
            logger.error("Can't get list of available garage places", exception);
        }
    }
}