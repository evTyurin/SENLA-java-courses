package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

import java.util.List;
import java.util.Scanner;

public class GetCraftsmenByOrder implements IAction {

    @Override
    public void execute() {

        System.out.println("Id of craftsman");
        Scanner scanner = new Scanner(System.in);
        long orderId = scanner.nextLong();
        List<Craftsman> craftsmen = CraftsmanService.getInstance().getCraftsmenByOrder(orderId);
        craftsmen.forEach(craftsman -> System.out.println(craftsman.toString()));
    }
}
