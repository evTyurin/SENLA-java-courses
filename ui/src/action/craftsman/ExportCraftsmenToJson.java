package action.craftsman;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class ExportCraftsmenToJson implements IAction {

    @Override
    public void execute() {

        System.out.println("Export to json");
        CraftsmanService
                .getInstance()
                .exportCraftsmenToJson();
    }
}
