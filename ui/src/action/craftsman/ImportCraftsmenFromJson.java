package action.craftsman;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class ImportCraftsmenFromJson implements IAction {

    @Override
    public void execute() {

        System.out.println("Import from json");
        CraftsmanService
                .getInstance()
                .importCraftsmenFromJson();
    }
}
