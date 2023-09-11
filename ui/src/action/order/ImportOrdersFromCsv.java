package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class ImportOrdersFromCsv implements IAction {

    @Override
    public void execute() {

        System.out.println("Import from csv");
        OrderService
                .getInstance()
                .importOrdersFromCsv();
    }
}
