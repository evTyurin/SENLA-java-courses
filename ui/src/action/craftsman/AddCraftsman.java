package action.craftsman;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;

import java.util.Scanner;

public class AddCraftsman implements IAction {

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter craftsman id ");
        String craftsmanId = scanner.nextLine();
        System.out.println("Enter craftsman name ");
        String craftsmanName = scanner.nextLine();
        System.out.println("Enter craftsman surname ");
        String craftsmanSurname = scanner.nextLine();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("id:")
                .append(craftsmanId)
                .append(",name:")
                .append(craftsmanName)
                .append(",surname:")
                .append(craftsmanSurname);

        CraftsmanService
                .getInstance()
                .addCraftsman(CraftsmanService
                        .getInstance()
                        .createCraftsmen(stringBuilder.toString()));
    }
}
