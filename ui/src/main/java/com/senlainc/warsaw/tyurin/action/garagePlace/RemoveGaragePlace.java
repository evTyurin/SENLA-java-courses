package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

import java.util.Scanner;

public class RemoveGaragePlace implements IAction {

    @Override
    public void execute() {

        System.out.println("Id of craftsman");
        Scanner scanner = new Scanner(System.in);
        long garagePlaceId = scanner.nextLong();
        GaragePlaceService.getInstance().removeGaragePlace(garagePlaceId);
    }
}