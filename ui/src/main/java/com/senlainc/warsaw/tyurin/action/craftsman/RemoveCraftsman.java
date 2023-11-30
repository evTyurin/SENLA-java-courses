package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

public class RemoveCraftsman implements IAction {

    private final static Logger logger = Logger.getLogger(RemoveCraftsman.class);

    private CraftsmanService craftsmanService;

    public RemoveCraftsman(CraftsmanService craftsmanService) {
        this.craftsmanService = craftsmanService;
    }

    @Override
    public void execute() {

        try {
            craftsmanService.removeCraftsmanById(1);
        } catch (Exception exception) {
            logger.error("Can't add craftsman", exception);
        }
    }
}
