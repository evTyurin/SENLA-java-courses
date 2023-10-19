package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

import java.util.List;

public class GetCraftsmenByOrder implements IAction {

    @Override
    public void execute() {

        List<Craftsman> craftsmen = CraftsmanService.getInstance().getCraftsmenByOrder(1);
        craftsmen.forEach(craftsman -> System.out.println(craftsman.toString()));
    }
}
