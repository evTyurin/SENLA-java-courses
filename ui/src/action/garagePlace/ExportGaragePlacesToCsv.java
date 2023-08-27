package action.garagePlace;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;

public class ExportGaragePlacesToCsv implements IAction {

    @Override
    public void execute() {

        System.out.println("Export to csv");
        GaragePlaceService
                .getInstance()
                .exportGaragePlacesToCsv();
    }
}
