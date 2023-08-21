package action.order;

import action.IAction;
import com.senlainc.warsaw.tyurin.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AddOrder implements IAction {

    @Override
    public void execute() {

        Scanner scanner = new Scanner(System.in);
        List<Long> craftsmenId = new ArrayList<>();
        long orderId = 0;
        double orderPrice = 0;
        int startYear = 0;
        int startMonth = 0;
        int startDayOfMonth = 0;
        int startHour = 0;
        int completionYear = 0;
        int completionMonth = 0;
        int completionDayOfMonth = 0;
        int completionHour = 0;
        long garagePlaceId = 0;

        do {
            System.out.println("Enter order id ");
            try {
                orderId = Long.parseLong(scanner.nextLine());
                if (orderId <= 0) {
                    System.out.println("Order id must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as order id");
            }
        } while (orderId <= 0);

        do {
            System.out.println("Enter order price ");
            try {
                orderPrice = Double.parseDouble(scanner.nextLine());
                if (orderPrice <= 0) {
                    System.out.println("Order price must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as order price");
            }
        } while (orderPrice <= 0);

        do {
            System.out.println("Enter start year ");
            try {
                startYear = Integer.parseInt(scanner.nextLine());
                if (startYear <= 0) {
                    System.out.println("Start year must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as start year");
            }
        } while (startYear <= 0);

        do {
            System.out.println("Enter start month ");
            try {
                startMonth = Integer.parseInt(scanner.nextLine());
                if (startMonth <= 0) {
                    System.out.println("Start month must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as start month");
            }
        } while (startMonth <= 0);

        do {
            System.out.println("Enter start day of month ");
            try {
                startDayOfMonth = Integer.parseInt(scanner.nextLine());
                if (startDayOfMonth <= 0) {
                    System.out.println("Start day of month must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as start day of month");
            }
        } while (startDayOfMonth <= 0);

        do {
            System.out.println("Enter start hour ");
            try {
                startHour = Integer.parseInt(scanner.nextLine());
                if (startHour <= 0) {
                    System.out.println("Start hour must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as start hour");
            }
        } while (startHour <= 0);

        do {
            System.out.println("Enter completion year ");
            try {
                completionYear = Integer.parseInt(scanner.nextLine());
                if (completionYear <= 0) {
                    System.out.println("Completion year must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as completion year");
            }
        } while (completionYear <= 0);

        do {
            System.out.println("Enter completion month ");
            try {
                completionMonth = Integer.parseInt(scanner.nextLine());
                if (completionMonth <= 0) {
                    System.out.println("Completion month must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as completion month");
            }
        } while (completionMonth <= 0);

        do {
            System.out.println("Enter completion day of month ");
            try {
                completionDayOfMonth = Integer.parseInt(scanner.nextLine());
                if (completionDayOfMonth <= 0) {
                    System.out.println("Completion day of month must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as completion day of month");
            }
        } while (completionDayOfMonth <= 0);

        do {
            System.out.println("Enter completion hour ");
            try {
                completionHour = Integer.parseInt(scanner.nextLine());
                if (completionHour <= 0) {
                    System.out.println("Completion hour must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as completion hour");
            }
        } while (completionHour <= 0);

        do {
            System.out.println("Enter garage place id ");
            try {
                garagePlaceId = Long.parseLong(scanner.nextLine());
                if (garagePlaceId <= 0) {
                    System.out.println("Garage place id must be bigger then 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as garage place id");
            }
        } while (garagePlaceId <= 0);

        addCraftsmenId(craftsmenId);

        OrderService
                .getInstance()
                .createOrder(orderId,
                        orderPrice,
                        LocalDateTime.of(startYear, startMonth, startDayOfMonth, startHour, 0),
                        LocalDateTime.of(completionYear, completionMonth, completionDayOfMonth, completionHour, 0),
                        craftsmenId,
                        garagePlaceId);
    }

    private void addCraftsmenId(List<Long> craftsmenId) {
        Scanner scanner = new Scanner(System.in);
        long craftsmanId = 0;
        do {
            System.out.println("Enter craftsmen id ");
            try {
                craftsmanId = Long.parseLong(scanner.nextLine());
                craftsmenId.add(craftsmanId);
                if (craftsmanId <= 0) {
                    System.out.println("Craftsman id must be bigger then 0");
                } else {
                    System.out.println("Do you want to add another craftsman?");
                    System.out.println("1 - yes, 2- no");
                    int choice = 0;
                    do {
                        System.out.println("Enter 1 or 2");
                        try {
                            choice = Integer.parseInt(scanner.nextLine());
                            switch (choice) {
                                case (1):
                                    addCraftsmenId(craftsmenId);
                                    break;
                                case (2):
                                    break;
                                default:
                                    System.out.println("You should choose 1 or 2");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("You enter not a number");
                        }
                    } while (choice == 1 || choice == 2);
                }
            } catch (NumberFormatException e) {
                System.out.println("You enter not a number as craftsman id");
            }
        } while (craftsmanId <= 0);
    }

}
