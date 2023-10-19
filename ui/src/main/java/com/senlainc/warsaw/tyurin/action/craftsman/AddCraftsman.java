package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class AddCraftsman implements IAction {

    @Override
    public void execute() {

        CraftsmanService
                .getInstance()
                .addCraftsman(CraftsmanService
                        .getInstance()
                        .createCraftsmen(4, "Nick", "Smart"));

        CraftsmanService
                .getInstance()
                .addCraftsman(CraftsmanService
                        .getInstance()
                        .createCraftsmen(8, "Jack", "Jones"));

        CraftsmanService
                .getInstance()
                .addCraftsman(CraftsmanService
                        .getInstance()
                        .createCraftsmen(9, "Star", "Wars"));
    }
}
