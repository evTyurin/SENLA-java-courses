package action.craftsman;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class ImportCraftsmen implements IAction {

    @Override
    public void execute() {

        System.out.println("Import from file");
        CraftsmanService
                .getInstance()
                .importCraftsmen();
    }
}
