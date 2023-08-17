package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetCurrentlyExecutedOrdersSortedByCompletionDate implements IAction {

    @Override
    public void execute() {

        System.out.println("List of currently executed orders sorted by completion date");

        OrderService
                .getInstance()
                .getCurrentlyExecutedOrdersSortedByCompletionDate()
                .forEach(order -> System.out.println(order.toString()));
    }
}