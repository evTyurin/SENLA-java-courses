package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class ExportOrders implements IAction {

    @Override
    public void execute() {

        System.out.println("Export to file");
        OrderService
                .getInstance()
                .exportOrders();
    }
}
