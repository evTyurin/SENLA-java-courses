package com.senlainc.warsaw.tyurin.entity;

import com.senlainc.warsaw.tyurin.util.OrderStatus;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "garage_place")
public class GaragePlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "number")
    private int number;
    @Column(name = "space")
    private double space;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "garage_place_id")
    private List<Order> orders;

    public GaragePlace() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getSpace() {
        return space;
    }

    public void setSpace(double space) {
        this.space = space;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GaragePlace that = (GaragePlace) o;
        return id == that.id &&
                number == that.number &&
                Double.compare(that.space, space) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, space);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(id)
                .append(",")
                .append(number)
                .append(",")
                .append(space);

        return stringBuilder.toString();
    }
}