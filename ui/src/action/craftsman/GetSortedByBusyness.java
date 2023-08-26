package action.craftsman;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class GetSortedByBusyness implements IAction {
    @Override
    public void execute() {

        System.out.println("List of craftsmen sorted by busyness");
        CraftsmanService
                .getInstance()
                .getSortedByBusyness()
                .forEach(craftsman -> System.out.println(craftsman.toString()));
    }
}
