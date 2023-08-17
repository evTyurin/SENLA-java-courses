package action.garagePlace;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class ExportGaragePlaces implements IAction {

    @Override
    public void execute() {

        System.out.println("Export to file");
        GaragePlaceService
                .getInstance()
                .exportGaragePlaces();
    }
}
