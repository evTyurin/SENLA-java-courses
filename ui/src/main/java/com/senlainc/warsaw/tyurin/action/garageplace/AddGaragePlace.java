package com.senlainc.warsaw.tyurin.action.garageplace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class AddGaragePlace implements IAction {

    private final static Logger logger = Logger.getLogger(AddGaragePlace.class);

    private GaragePlaceService garagePlaceService;

    public AddGaragePlace(GaragePlaceService garagePlaceService) {
        this.garagePlaceService = garagePlaceService;
    }

    @Override
    public void execute() {

        try {
            garagePlaceService
                    .addGaragePlace(garagePlaceService
                            .createGaragePlace(11, 15));
        } catch (Exception exception) {
            logger.error("Can't add garage place", exception);
        }
    }
}