package com.senlainc.warsaw.tyurin.action.garageplace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class AddGaragePlace implements IAction {

    private final static Logger logger = Logger.getLogger(AddGaragePlace.class);

    @Override
    public void execute() {

        try {
            GaragePlaceService
                    .getInstance()
                    .addGaragePlace(GaragePlaceService
                            .getInstance()
                            .createGaragePlace(11, 15));

            GaragePlaceService
                    .getInstance()
                    .addGaragePlace(GaragePlaceService
                            .getInstance()
                            .createGaragePlace(15, 25));

            GaragePlaceService
                    .getInstance()
                    .addGaragePlace(GaragePlaceService
                            .getInstance()
                            .createGaragePlace(7, 15));
        } catch (Exception exception) {
            logger.error("Can't add garage place", exception);
        }
    }
}