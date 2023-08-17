package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetArchivedOrdersSortedByCompletionDate implements IAction {

    @Override
    public void execute() {

        System.out.println("List of archived orders sorted by completion date");
        OrderService
                .getInstance()
                .getArchivedOrdersSortedByCompletionDate()
                .forEach(order -> System.out.println(order.toString()));
    }
}