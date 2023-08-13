package menu;

import java.util.Scanner;

public class MenuController {
    private static MenuController INSTANCE;
    private Builder builder;
    private Navigator navigator;

    private MenuController() {
        builder = Builder.getInstance();
        navigator = Navigator.getInstance();
    }

    public static MenuController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuController();
        }
        return INSTANCE;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
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
