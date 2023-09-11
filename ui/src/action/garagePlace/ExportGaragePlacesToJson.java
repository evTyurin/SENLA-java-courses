package action.garagePlace;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class ExportGaragePlacesToJson implements IAction {

    @Override
    public void execute() {

        System.out.println("Export to json");
        GaragePlaceService
                .getInstance()
                .exportGaragePlacesToJson();
    }
}
