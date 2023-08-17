package action.garagePlace;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class ImportGaragePlaces implements IAction {

    @Override
    public void execute() {

        System.out.println("Import from file");
        GaragePlaceService
                .getInstance()
                .importGaragePlaces();
    }
}
