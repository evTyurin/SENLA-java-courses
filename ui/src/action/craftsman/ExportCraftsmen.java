package action.craftsman;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class ExportCraftsmen implements IAction {

    @Override
    public void execute() {

        System.out.println("Export to file");
        CraftsmanService
                .getInstance()
                .exportCraftsmen();
    }
}
