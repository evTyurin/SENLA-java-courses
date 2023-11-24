package com.senlainc.warsaw.tyurin.entity;

import com.senlainc.warsaw.tyurin.util.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "order")
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
    @Transient
    private OrderStatus orderStatus;
    @Transient
    private List<Long> craftsmenId;
    @Column(name = "garage_place_id")
    private long garagePlaceId;

    public Order() {
        orderStatus = OrderStatus.NEW;
        craftsmenId = new ArrayList<>();
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

    public List<Long> getCraftsmenId() {
        return craftsmenId;
    }

    public void setCraftsmen(List<Long> craftsmenId) {
        this.craftsmenId = craftsmenId;
    }

    public void addCraftsmanId(Craftsman craftsman) {
        craftsmenId.add(craftsman.getId());
    }

    public void removeCraftsman(Craftsman craftsman) {
        craftsmenId.remove(craftsman.getId());
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

    public long getGaragePlaceId() {
        return garagePlaceId;
    }

    public void setGaragePlaceId(long garagePlaceId) {
        this.garagePlaceId = garagePlaceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.price, price) == 0 &&
                garagePlaceId == order.garagePlaceId &&
                submissionDate.equals(order.submissionDate) &&
                startDate.equals(order.startDate) &&
                completionDate.equals(order.completionDate) &&
                orderStatus == order.orderStatus &&
                craftsmenId.equals(order.craftsmenId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                price,
                submissionDate,
                startDate,
                completionDate,
                orderStatus,
                craftsmenId,
                garagePlaceId);
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
        craftsmenId.forEach(id -> stringBuilder
                .append(id)
                .append(";"));

        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(";"));

        stringBuilder
                .append(",")
                .append(garagePlaceId);

        return stringBuilder.toString();
    }
}