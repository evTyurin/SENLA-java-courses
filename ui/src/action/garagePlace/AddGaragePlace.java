package action.garagePlace;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

import java.util.Scanner;

public class AddGaragePlace implements IAction {

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter garage place id ");
        String garagePlaceId = scanner.nextLine();
        System.out.println("Enter garage place number ");
        String garagePlaceNumber = scanner.nextLine();
        System.out.println("Enter garage place space ");
        String garagePlaceSpace = scanner.nextLine();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("id:")
                .append(garagePlaceId)
                .append(",number:")
                .append(garagePlaceNumber)
                .append(",space:")
                .append(garagePlaceSpace);

        GaragePlaceService
                .getInstance()
                .addGaragePlace(GaragePlaceService
                        .getInstance()
                        .createGaragePlace(stringBuilder.toString()));
    }
}