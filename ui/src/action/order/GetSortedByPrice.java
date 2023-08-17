package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetSortedByPrice implements IAction {
    @Override
    public void execute() {

        System.out.println("List of orders sorted by price");

        OrderService
                .getInstance()
                .getSortedByPrice()
                .forEach(order -> System.out.println(order.toString()));
    }
}