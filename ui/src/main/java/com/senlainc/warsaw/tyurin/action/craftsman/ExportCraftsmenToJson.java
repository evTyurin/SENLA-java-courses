package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class ExportCraftsmenToJson implements IAction {

    private final static Logger logger = Logger.getLogger(ExportCraftsmenToJson.class);

    private CraftsmanService craftsmanService;

    public ExportCraftsmenToJson(CraftsmanService craftsmanService) {
        this.craftsmanService = craftsmanService;
    }

    @Override
    public void execute() {

        System.out.println("Export to json");
        try {
            craftsmanService
                    .exportCraftsmenToJson();
        } catch (Exception exception) {
            logger.error("Can't export craftsman to json", exception);
        }
    }
}
