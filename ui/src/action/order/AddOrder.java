package action.order;

import action.IAction;

import java.time.LocalDateTime;
import java.util.Scanner;

public class AddOrder implements IAction {

    @Override
    public void execute() {

        StringBuilder orderBuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id ");
        String orderId = scanner.nextLine();
        orderBuilder
                .append("id:")
                .append(orderId);
        System.out.println("Enter price ");
        String price = scanner.nextLine();
        orderBuilder
                .append(",price:")
                .append(price);

        System.out.println("Enter start year ");
        int startYear = scanner.nextInt();
        System.out.println("Enter start month ");
        int startMonth = scanner.nextInt();
        System.out.println("Enter start day of month ");
        int startDayOfMonth = scanner.nextInt();
        System.out.println("Enter start hour ");
        int startHour = scanner.nextInt();

        orderBuilder
                .append(",startDate:")
                .append(LocalDateTime.of(startYear, startMonth, startDayOfMonth, startHour, 0));

        System.out.println("Enter completion year ");
        int completionYear = scanner.nextInt();
        System.out.println("Enter completion month ");
        int completionMonth = scanner.nextInt();
        System.out.println("Enter completion day of month ");
        int completionDayOfMonth = scanner.nextInt();
        System.out.println("Enter completion hour ");
        int completionHour = scanner.nextInt();

        orderBuilder
                .append(",completionDate:")
                .append(LocalDateTime.of(completionYear, completionMonth, completionDayOfMonth, completionHour, 0));

        addCraftsmanId(orderBuilder);

        System.out.println("Garage place id ");
        long garagePlaceId = scanner.nextLong();
        orderBuilder
                .append(",garagePlaceId:")
                .append(garagePlaceId);
    }

    private String addCraftsmanId(StringBuilder orderBuilder) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter craftsman id ");
        String craftsmanId = scanner.nextLine();
        orderBuilder.append(craftsmanId);

        System.out.println("Do you want to add another craftsman?");
        System.out.println("1 - yes, 2- no");

        int choice = scanner.nextInt();

        switch (choice) {
            case (1):
                orderBuilder.append(";");
                addCraftsmanId(orderBuilder);
                break;
            case (2):
                break;
            }
        return orderBuilder.toString();
    }

}
