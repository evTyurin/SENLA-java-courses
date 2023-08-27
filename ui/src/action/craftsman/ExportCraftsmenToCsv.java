package action.craftsman;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class ExportCraftsmenToCsv implements IAction {

    @Override
    public void execute() {

        System.out.println("Export to csv");
        CraftsmanService
                .getInstance()
                .exportCraftsmenToCsv();
    }
}
