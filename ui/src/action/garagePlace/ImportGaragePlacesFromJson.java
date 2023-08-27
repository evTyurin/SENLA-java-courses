package action.garagePlace;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class ImportGaragePlacesFromJson implements IAction {

    @Override
    public void execute() {

        System.out.println("Import from json");
        GaragePlaceService
                .getInstance()
                .importGaragePlacesFromJson();
    }
}
