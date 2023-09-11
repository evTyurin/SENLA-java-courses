package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

import java.util.Scanner;

public class RemoveOrder implements IAction {

    @Override
    public void execute() {

        System.out.println("Id of order");
        Scanner scanner = new Scanner(System.in);
        long orderId = scanner.nextLong();
        OrderService.getInstance().removeOrder(orderId);
    }
}
