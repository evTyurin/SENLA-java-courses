package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class GetSortedByBusyness implements IAction {
    @Override
    public void execute() throws Exception {

        System.out.println("List of craftsmen sorted by busyness");
        CraftsmanService
                .getInstance()
                .getSortedByBusyness()
                .forEach(craftsman -> System.out.println(craftsman.toString()));
    }
}
