package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class ExportCraftsmenToJson implements IAction {

    @Override
    public void execute() throws Exception {

        System.out.println("Export to json");
        CraftsmanService
                .getInstance()
                .exportCraftsmenToJson();
    }
}
