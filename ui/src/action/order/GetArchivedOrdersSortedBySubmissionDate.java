package action.order;

import action.IAction;
import util.Printer;
import com.senlainc.warsaw.tyurin.service.OrderService;

public class GetArchivedOrdersSortedBySubmissionDate implements IAction {

    @Override
    public void execute() {

        System.out.println("List of archived orders sorted by completion date");

        OrderService
                .getInstance()
                .getArchivedOrdersSortedByCompletionDate()
                .forEach(Printer::printOrder);
                    }
}