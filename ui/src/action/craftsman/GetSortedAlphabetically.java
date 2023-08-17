package action.craftsman;

import com.senlainc.warsaw.tyurin.service.CraftsmanService;

import action.IAction;

public class GetSortedAlphabetically implements IAction {

    @Override
    public void execute() {

        System.out.println("List of craftsmen sorted alphabetically");
        CraftsmanService
                .getInstance()
                .getSortedAlphabetically()
                .forEach(craftsman -> System.out.println(craftsman.toString()));
    }
}
