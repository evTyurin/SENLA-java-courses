package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.menu.MenuController;

public class Test {

    public static void main(String[] args) throws Exception {

        ApplicationContext context = ApplicationStarter.run("com.senlainc.warsaw.tyurin");
        MenuController menuController = context.getObject(MenuController.class);
        menuController.run();
    }
}
