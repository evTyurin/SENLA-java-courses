package action.order;

import action.IAction;
import util.Printer;
import com.senlainc.warsaw.tyurin.service.OrderService;

import java.util.Scanner;

public class GetOrderByCraftsman implements IAction {
    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter craftsman id ");
        long craftsmanId = scanner.nextLong();
        Printer.printOrder(OrderService.getInstance().getOrderByCraftsmen(craftsmanId));
    }
}