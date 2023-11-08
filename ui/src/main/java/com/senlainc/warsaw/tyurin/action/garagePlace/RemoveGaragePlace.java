package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class RemoveGaragePlace implements IAction {

    @Override
    public void execute() throws Exception {

        GaragePlaceService.getInstance().removeGaragePlace(1);
    }
}