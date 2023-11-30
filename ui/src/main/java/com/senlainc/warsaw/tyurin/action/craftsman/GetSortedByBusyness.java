package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class GetSortedByBusyness implements IAction {

    private final static Logger logger = Logger.getLogger(GetSortedByBusyness.class);

    private CraftsmanService craftsmanService;

    public GetSortedByBusyness(CraftsmanService craftsmanService) {
        this.craftsmanService = craftsmanService;
    }

    @Override
    public void execute() {

        System.out.println("List of craftsmen sorted by busyness");
        try {
            craftsmanService
                    .getSortedByBusyness()
                    .forEach(craftsman -> System.out.println(craftsman.toString()));
        } catch (Exception exception) {
            logger.error("Can't get list of craftsmen sorted by busyness", exception);
        }
    }
}
