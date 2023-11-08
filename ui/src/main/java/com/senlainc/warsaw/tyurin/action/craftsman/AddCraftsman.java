package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

public class AddCraftsman implements IAction {

    @Override
    public void execute() throws Exception {

        CraftsmanService
                .getInstance()
                .addCraftsman(CraftsmanService
                        .getInstance()
                        .createCraftsmen("Nick", "Smart"));

        CraftsmanService
                .getInstance()
                .addCraftsman(CraftsmanService
                        .getInstance()
                        .createCraftsmen("Jack", "Jones"));

        CraftsmanService
                .getInstance()
                .addCraftsman(CraftsmanService
                        .getInstance()
                        .createCraftsmen("Star", "Wars"));
    }
}
