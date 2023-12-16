package com.senlainc.warsaw.tyurin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

import javax.persistence.*;
import javax.validation.Valid;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private double price;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime submissionDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime completionDate;
    private OrderStatus orderStatus;
    @Valid
    private GaragePlaceDto garagePlace;
    @Valid
    private List<CraftsmanDto> craftsmen;

    public long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(long id) {
        this.id = id;
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

    @JsonIgnore
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public GaragePlaceDto getGaragePlace() {
        return garagePlace;
    }

    public void setGaragePlace(GaragePlaceDto garagePlace) {
        this.garagePlace = garagePlace;
    }

    public List<CraftsmanDto> getCraftsmen() {
        return craftsmen;
    }

    public void setCraftsmen(List<CraftsmanDto> craftsmen) {
        this.craftsmen = craftsmen;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", price=" + price +
                ", submissionDate=" + submissionDate +
                ", startDate=" + startDate +
                ", completionDate=" + completionDate +
                ", orderStatus=" + orderStatus +
                ", garagePlace=" + garagePlace +
                ", craftsmen=" + craftsmen +
                '}';
    }
}
