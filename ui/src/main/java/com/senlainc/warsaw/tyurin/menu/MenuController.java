package com.senlainc.warsaw.tyurin.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuController {

    private static MenuController INSTANCE;

    @Autowired
    private Builder builder;
    @Autowired
    private Navigator navigator;

    public MenuController() {}

    public void run() {
        Scanner scanner = new Scanner(System.in);
        builder.buildMenu();
        navigator.setCurrentMenu(builder.getRootMenu());
        while (true) {

            navigator.printMenu();

            try {
                int input = scanner.nextInt();
                navigator.navigate(input);
            } catch (Exception exception) {
                exception.getStackTrace();
            }
        }
    }
}
