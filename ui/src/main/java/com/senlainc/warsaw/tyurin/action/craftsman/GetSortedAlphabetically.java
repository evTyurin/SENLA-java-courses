package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class GetSortedAlphabetically implements IAction {

    private final static Logger logger = Logger.getLogger(GetSortedAlphabetically.class);

    @Override
    public void execute() {

        System.out.println("List of craftsmen sorted alphabetically");
        try {
            CraftsmanService
                    .getInstance()
                    .getSortedAlphabetically()
                    .forEach(craftsman -> System.out.println(craftsman.toString()));
        } catch (Exception exception) {
            logger.error("Can't get list of craftsman sorted alphabetically", exception);
        }
    }
}
