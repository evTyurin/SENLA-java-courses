package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class ImportCraftsmenFromJson implements IAction {

    @Override
    public void execute() {

        System.out.println("Import from json");
        CraftsmanService
                .getInstance()
                .importCraftsmenFromJson();
    }
}
