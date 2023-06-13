package com.senlainc.warsaw.tyurin;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private Appointment timeAppointment;
    private OrderStatus orderStatus;
    private double spaceForVehicle;
    private List<Craftsman> craftsmen;

    public Order() {
        orderStatus = OrderStatus.NEW;
        craftsmen = new ArrayList<>();
        timeAppointment = new Appointment();
    }

    public Appointment getTimeAppointment() {
        return timeAppointment;
    }

    public void setTimeAppointment(Appointment timeAppointment) {
        this.timeAppointment = timeAppointment;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getSpaceForVehicle() {
        return spaceForVehicle;
    }

    public void setSpaceForVehicle(double spaceForVehicle) {
        this.spaceForVehicle = spaceForVehicle;
    }

    public List<Craftsman> getCraftsmen() {
        return craftsmen;
    }

    public void setCraftsmen(List<Craftsman> craftsmen) {
        this.craftsmen = craftsmen;
    }

    public void addCraftsman(Craftsman craftsman) {
        craftsmen.add(craftsman);
    }

    public void removeCraftsman(Craftsman craftsman) {
        craftsmen.remove(craftsman);
    }

    public void changeStatusToClose() {
        this.orderStatus = OrderStatus.CLOSE;
    }

    public void changeStatusToCancel() {
        this.orderStatus = OrderStatus.CANCEL;
    }
}
