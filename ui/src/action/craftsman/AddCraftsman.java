package action.craftsman;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

import java.util.Scanner;

public class AddCraftsman implements IAction {

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        long craftsmanId = 0;
        do {
            System.out.println("Enter craftsman id ");
            try {
                craftsmanId = Long.parseLong(scanner.nextLine());
                if (craftsmanId <= 0) {
                    System.out.println("Craftsman id must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as craftsman id");
            }
        } while (craftsmanId <= 0);

        System.out.println("Enter craftsman name ");
        String craftsmanName = scanner.nextLine();
        System.out.println("Enter craftsman surname ");
        String craftsmanSurname = scanner.nextLine();

        CraftsmanService
                .getInstance()
                .addCraftsman(CraftsmanService
                        .getInstance()
                        .createCraftsmen(craftsmanId, craftsmanName, craftsmanSurname));
    }
}
