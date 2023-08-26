package action.garagePlace;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

import java.util.Scanner;

public class AddGaragePlace implements IAction {

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        long garagePlaceId = 0;
        int garagePlaceNumber = 0;
        double garagePlaceSpace = 0;
        do {
            System.out.println("Enter garage place id ");
            try {
                garagePlaceId = Long.parseLong(scanner.nextLine());
                if (garagePlaceId <= 0) {
                    System.out.println("Garage place id must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as garage place id");
            }
        } while (garagePlaceId <= 0);

        do {
            System.out.println("Enter garage place number ");
            try {
                garagePlaceNumber = Integer.parseInt(scanner.nextLine());
                if (garagePlaceNumber <= 0) {
                    System.out.println("Garage place number must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as garage place number");
            }
        } while (garagePlaceNumber <= 0);

        do {
            System.out.println("Enter garage place space");
            try {
                garagePlaceSpace = Double.parseDouble(scanner.nextLine());
                if (garagePlaceSpace <= 0) {
                    System.out.println("Garage place space must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as garage place space");
            }
        } while (garagePlaceSpace <= 0);

        GaragePlaceService
                .getInstance()
                .addGaragePlace(GaragePlaceService
                        .getInstance()
                        .createGaragePlace(garagePlaceId, garagePlaceNumber, garagePlaceSpace));
    }
}