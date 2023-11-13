package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class ImportCraftsmenFromJson implements IAction {

    private final static Logger logger = Logger.getLogger(ImportCraftsmenFromJson.class);

    @Override
    public void execute() {

        System.out.println("Import from json");
        try {
            CraftsmanService
                    .getInstance()
                    .importCraftsmenFromJson();
        } catch (Exception exception) {
            logger.error("Can't import craftsman from json", exception);
        }
    }
}
