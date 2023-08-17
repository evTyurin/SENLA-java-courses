package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetArchivedOrdersSortedByPrice implements IAction {

    @Override
    public void execute() {

        System.out.println("List of archived orders sorted by price");
        OrderService
                .getInstance()
                .getArchivedOrdersSortedByPrice()
                .forEach(order -> System.out.println(order.toString()));
    }
}