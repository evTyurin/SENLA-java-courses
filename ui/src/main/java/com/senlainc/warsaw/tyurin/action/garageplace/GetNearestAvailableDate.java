package com.senlainc.warsaw.tyurin.action.garageplace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class GetNearestAvailableDate implements IAction {

    private final static Logger logger = Logger.getLogger(GetNearestAvailableDate.class);

    private GaragePlaceService garagePlaceService;

    public GetNearestAvailableDate(GaragePlaceService garagePlaceService) {
        this.garagePlaceService = garagePlaceService;
    }

    @Override
    public void execute() {

        try {
            System.out.println("Nearest available date = "
                    + garagePlaceService.getNearestAvailableDate());
        } catch (Exception exception) {
            logger.error("Can't get nearest available date", exception);
        }
    }
}