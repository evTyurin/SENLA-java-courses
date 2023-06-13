package com.senlainc.warsaw.tyurin;

import java.time.LocalDate;
import java.time.LocalTime;

public class Test {

    public static void main(String[] args) {

        Garage garage = Garage.getInstance();

        System.out.println("Create new order");
        Order order = new Order();
        garage.addOrder(order);

        System.out.println("Close order");
        order.changeStatusToClose();

        System.out.println("Cancel order");
        order.changeStatusToCancel();

        System.out.println("Delete order");
        garage.deleteOrder(order);

        Craftsman craftsman = new Craftsman();

        System.out.println("Add craftsmen. Hired as garage worker");
        garage.addCraftsman(craftsman);
        System.out.println("Remove craftsman. Got fired from garage");
        garage.removeCraftsman(craftsman);

        System.out.println("Add garage space");
        garage.addSpace(10);
        System.out.println("Delete garage space");
        garage.deleteSpace(10);

        order = new Order();
        System.out.println("Add craftsmen as order performer");
        order.addCraftsman(craftsman);
        System.out.println("Remove craftsman as order performer");
        order.removeCraftsman(craftsman);

        System.out.println("Shift the time of order");
        order.getTimeAppointment().setStartTime(LocalTime.of(5, 30));
        order.getTimeAppointment().setStartDate(LocalDate.of(2023,6,15));
    }
}
