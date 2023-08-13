package action.order;

import action.IAction;
import util.Printer;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetSortedBySubmissionDate implements IAction {
    @Override
    public void execute() {

        System.out.println("List of orders sorted by submission date");

        OrderService
                .getInstance()
                .getSortedBySubmissionDate()
                .forEach(Printer::printOrder);
    }
}