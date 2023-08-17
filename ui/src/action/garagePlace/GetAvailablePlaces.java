package action.garagePlace;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class GetAvailablePlaces implements IAction {

    @Override
    public void execute() {

        System.out.println("List of available garage places");
        GaragePlaceService
                .getInstance()
                .getAvailablePlaces()
                .forEach(garagePlace -> System.out.println(garagePlace.toString()));
    }
}