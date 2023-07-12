package com.senlainc.warsaw.tyurin.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {

    private long id;
    private double price;
    private LocalDateTime submissionDate;
    private LocalDateTime startDate;
    private LocalDateTime completionDate;
    private OrderStatus orderStatus;
    private List<Craftsman> craftsmen;
    private int garagePlaceNumber;

    public Order() {
        orderStatus = OrderStatus.NEW;
        craftsmen = new ArrayList<>();
        submissionDate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Craftsman> getCraftsmen() {
        return craftsmen;
    }

    public void setCraftsmen(List<Craftsman> craftsmen) {
        this.craftsmen = craftsmen;
    }

    public void addCraftsman(Craftsman craftsman) {
        craftsmen.add(craftsman);
        craftsman.getSchedule().replace(this.startDate, false);
    }

    public void removeCraftsman(Craftsman craftsman) {
        craftsmen.remove(craftsman);
    }

    public void changeStatusToClose() {
        this.orderStatus = OrderStatus.COMPLETED;
    }

    public void changeStatusToCancel() {
        this.orderStatus = OrderStatus.CANCELED;
    }

    public void setStatusToClose() {
        this.orderStatus = OrderStatus.COMPLETED;
    }

    public void setStatusToCancel() {
        this.orderStatus = OrderStatus.CANCELED;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public int getGaragePlaceNumber() {
        return garagePlaceNumber;
    }

    public void setGaragePlaceNumber(int garagePlaceNumber) {
        this.garagePlaceNumber = garagePlaceNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.price, price) == 0 &&
                garagePlaceNumber == order.garagePlaceNumber &&
                Objects.equals(submissionDate, order.submissionDate) &&
                Objects.equals(startDate, order.startDate) &&
                Objects.equals(completionDate, order.completionDate) &&
                orderStatus == order.orderStatus &&
                Objects.equals(craftsmen, order.craftsmen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, submissionDate, startDate, completionDate, orderStatus, craftsmen, garagePlaceNumber);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", submissionDate=" + submissionDate +
                ", startDate=" + startDate +
                ", completionDate=" + completionDate +
                ", orderStatus=" + orderStatus +
                ", craftsmen=" + craftsmen +
                ", garagePlaceNumber=" + garagePlaceNumber +
                '}';
    }
}