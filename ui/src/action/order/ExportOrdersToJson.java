package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class ExportOrdersToJson implements IAction {

    @Override
    public void execute() {

        System.out.println("Export to json");
        OrderService
                .getInstance()
                .exportOrdersToJson();
    }
}
