package com.senlainc.warsaw.tyurin.entity;

import com.senlainc.warsaw.tyurin.util.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "price")
    private double price;
    @Column(name = "submission_date")
    private LocalDateTime submissionDate;
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "completion_date")
    private LocalDateTime completionDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "garage_place_id")
    private GaragePlace garagePlace;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "craftsman_orders",
            joinColumns = @JoinColumn(name = "orders_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "craftsman_id", referencedColumnName = "id")
    )
    private List<Craftsman> craftsmen;

    public Order() {
        orderStatus = OrderStatus.NEW;
        submissionDate = LocalDateTime.now();
        submissionDate = submissionDate
                .minusSeconds(submissionDate.getSecond())
                .minusNanos(submissionDate.getNano());
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

    public GaragePlace getGaragePlace() {
        return garagePlace;
    }

    public void setGaragePlace(GaragePlace garagePlace) {
        this.garagePlace = garagePlace;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.price, price) == 0 &&
                garagePlace == order.garagePlace &&
                submissionDate.equals(order.submissionDate) &&
                startDate.equals(order.startDate) &&
                completionDate.equals(order.completionDate) &&
                orderStatus == order.orderStatus &&
                craftsmen.equals(order.craftsmen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                price,
                submissionDate,
                startDate,
                completionDate,
                orderStatus,
                craftsmen,
                garagePlace);
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(id)
                .append(",")
                .append(price)
                .append(",")
                .append(submissionDate)
                .append(",")
                .append(startDate)
                .append(",")
                .append(completionDate)
                .append(",")
                .append(orderStatus)
                .append(",");
        craftsmen.forEach(craftsman -> stringBuilder
                .append(craftsman)
                .append(";"));

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(";"));

        stringBuilder
                .append(",")
                .append(garagePlace);

        return stringBuilder.toString();
    }
}
