package action.garagePlace;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class GetNearestAvailableDate implements IAction {

    @Override
    public void execute() {

        System.out.println("Nearest available date = " + GaragePlaceService.getInstance().getNearestAvailableDate());

    }
}