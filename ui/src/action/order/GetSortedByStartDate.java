package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetSortedByStartDate implements IAction {
    @Override
    public void execute() {

        System.out.println("List of orders sorted by start date");

        OrderService
                .getInstance()
                .getSortedByStartDate()
                .forEach(order -> System.out.println(order.toString()));
    }
}