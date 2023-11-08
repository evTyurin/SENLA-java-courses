package com.senlainc.warsaw.tyurin.menu;

public class Navigator {

    private static Navigator INSTANCE;

    private Menu currentMenu;

    public static Navigator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Navigator();
        }
        return INSTANCE;
    }

    public Navigator() {}

    public void printMenu() {

        for (int menuItemNumber = 1; menuItemNumber <= currentMenu.getMenuItems().size(); menuItemNumber++) {
            System.out.println(menuItemNumber + " - " + currentMenu.getMenuItems().get(menuItemNumber - 1).getTitle());
        }
    }

    public void navigate(Integer integer) throws Exception {

        if (integer < 1 || integer > currentMenu.getMenuItems().size()) {
            System.out.println("Incorrect action. Please, try again");
        }

        if (currentMenu == currentMenu.getMenuItems().get(integer - 1).getNextMenu()) {
            currentMenu.getMenuItems().get(integer - 1).doAction();
        } else {
            currentMenu = currentMenu.getMenuItems().get(integer - 1).getNextMenu();
        }
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
