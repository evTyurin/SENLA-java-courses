package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class ExportCraftsmenToJson implements IAction {

    private final static Logger logger = Logger.getLogger(ExportCraftsmenToJson.class);

    @Override
    public void execute() throws Exception {

        System.out.println("Export to json");
        try {
            CraftsmanService
                    .getInstance()
                    .exportCraftsmenToJson();
        } catch (Exception exception) {
            logger.error("Can't export craftsman to json", exception);
        }
    }
}
