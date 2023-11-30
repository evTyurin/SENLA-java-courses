package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class AddCraftsman implements IAction {

    private final static Logger logger = Logger.getLogger(AddCraftsman.class);

    private CraftsmanService craftsmanService;

    public AddCraftsman(CraftsmanService craftsmanService) {
        this.craftsmanService = craftsmanService;
    }

    @Override
    public void execute() {

        try {
            craftsmanService.createCraftsmen("Star", "Wars");
        } catch (Exception exception) {
            logger.error("Can't add craftsman", exception);
        }
    }
}
