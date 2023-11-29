package com.senlainc.warsaw.tyurin.menu;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;

import java.util.Scanner;

@DependencyClass
public class MenuController {

    private static MenuController INSTANCE;

    @DependencyComponent
    private Builder builder;
    @DependencyComponent
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
