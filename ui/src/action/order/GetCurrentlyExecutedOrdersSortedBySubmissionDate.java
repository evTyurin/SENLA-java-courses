package action.order;

import action.IAction;
import util.Printer;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetCurrentlyExecutedOrdersSortedBySubmissionDate implements IAction {
    @Override
    public void execute() {

        System.out.println("List of currently executed orders sorted by submission date");

        OrderService
                .getInstance()
                .getCurrentlyExecutedOrdersSortedBySubmissionDate()
                .forEach(Printer::printOrder);
    }
}