package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class ImportOrdersFromJson implements IAction {

    @Override
    public void execute() {

        System.out.println("Import from json");
        OrderService
                .getInstance()
                .importOrdersFromJson();
    }
}
