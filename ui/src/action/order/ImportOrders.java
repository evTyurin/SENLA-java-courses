package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class ImportOrders implements IAction {

    @Override
    public void execute() {

        System.out.println("Import from file");
        OrderService
                .getInstance()
                .importOrders();
    }
}
