package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.apache.log4j.Logger;

public class GetNearestAvailableDate implements IAction {

    private final static Logger logger = Logger.getLogger(GetNearestAvailableDate.class);

    @Override
    public void execute() throws Exception {

        try {
            System.out.println("Nearest available date = "
                    + GaragePlaceService.getInstance().getNearestAvailableDate());
        } catch (Exception exception) {
            logger.error("Can't get nearest available date", exception);
        }
    }
}