package com.senlainc.warsaw.tyurin.action.craftsman;

import com.senlainc.warsaw.tyurin.action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

import java.util.Scanner;

public class RemoveCraftsman implements IAction {

    @Override
    public void execute() {

        System.out.println("Id of craftsman");
        Scanner scanner = new Scanner(System.in);
        long craftsmanId = scanner.nextLong();
        CraftsmanService.getInstance().removeCraftsmanById(craftsmanId);
    }
}
