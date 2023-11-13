package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class ImportCraftsmenFromCsv implements IAction {

    private final static Logger logger = Logger.getLogger(ImportCraftsmenFromCsv.class);

    @Override
    public void execute() {

        System.out.println("Import from csv");
        try {
            CraftsmanService
                    .getInstance()
                    .importCraftsmenFromCsv();
        } catch (Exception exception) {
            logger.error("Can't craftsman import from csv", exception);
        }
    }
}
