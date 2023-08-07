package action.order;

import action.IAction;
import util.Printer;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetSortedByCompletionDate implements IAction {
    @Override
    public void execute() {

        System.out.println("List of orders sorted by completion date");

        OrderService
                .getInstance()
                .getSortedByCompletionDate()
                .forEach(Printer::printOrder);
    }
}
