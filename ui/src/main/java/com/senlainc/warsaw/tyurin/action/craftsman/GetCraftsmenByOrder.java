package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.apache.log4j.Logger;

import java.util.List;

public class GetCraftsmenByOrder implements IAction {

    private final static Logger logger = Logger.getLogger(GetCraftsmenByOrder.class);

    private CraftsmanService craftsmanService;

    public GetCraftsmenByOrder(CraftsmanService craftsmanService) {
        this.craftsmanService = craftsmanService;
    }

    @Override
    public void execute() {

        try {
            List<Craftsman> craftsmen = craftsmanService.getCraftsmenByOrder(1);
            craftsmen.forEach(craftsman -> System.out.println(craftsman.toString()));
        } catch (Exception exception) {
            logger.error("Can't get craftsman by order", exception);
        }
    }
}
