package com.senlainc.warsaw.tyurin.action.garageplace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class RemoveGaragePlace implements IAction {

    private final static Logger logger = Logger.getLogger(RemoveGaragePlace.class);

    private GaragePlaceService garagePlaceService;

    public RemoveGaragePlace(GaragePlaceService garagePlaceService) {
        this.garagePlaceService = garagePlaceService;
    }

    @Override
    public void execute() throws Exception {

        try {
            garagePlaceService.removeGaragePlace(1);
        } catch (Exception exception) {
            logger.error("Can't remove garage place", exception);
        }
    }
}