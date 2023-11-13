package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class AddCraftsman implements IAction {

    private final static Logger logger = Logger.getLogger(AddCraftsman.class);

    @Override
    public void execute() throws Exception {

        try {
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
        } catch (Exception exception) {
            logger.error("Can't add craftsman", exception);
        }
    }
}
