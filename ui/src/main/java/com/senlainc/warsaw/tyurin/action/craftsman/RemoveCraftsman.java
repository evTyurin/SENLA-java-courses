package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class RemoveCraftsman implements IAction {

    @Override
    public void execute() throws Exception {

        CraftsmanService.getInstance().removeCraftsmanById(1);
    }
}
