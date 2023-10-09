package com.senlainc.warsaw.tyurin.action.garagePlace;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class GetAvailablePlacesAmount implements IAction {

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter year ");
        int year = scanner.nextInt();
        System.out.println("Enter month ");
        int month = scanner.nextInt();
        System.out.println("Enter day of month ");
        int dayOfMonth = scanner.nextInt();
        System.out.println("Enter hour ");
        int hour = scanner.nextInt();

        System.out.println("Amount of available places = " + GaragePlaceService
                .getInstance()
                .getAvailablePlacesAmount(LocalDateTime.of(year, month, dayOfMonth, hour, 0)));

    }
}