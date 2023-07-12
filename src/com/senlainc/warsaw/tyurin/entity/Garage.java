package com.senlainc.warsaw.tyurin.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Garage {

    private long id;
    private List<Craftsman> craftsmen;
    private List<Order> orders;
    private List<GaragePlace> places;

    public Garage() {
        craftsmen = new ArrayList<>();
        orders = new ArrayList<>();
        places = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Craftsman> getCraftsmen() {
        return craftsmen;
    }

    public void setCraftsmen(List<Craftsman> craftsmen) {
        this.craftsmen = craftsmen;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<GaragePlace> getPlaces() {
        return places;
    }

    public void setPlaces(List<GaragePlace> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garage garage = (Garage) o;
        return id == garage.id &&
                Objects.equals(craftsmen, garage.craftsmen) &&
                Objects.equals(orders, garage.orders) &&
                Objects.equals(places, garage.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, craftsmen, orders, places);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "id=" + id +
                ", craftsmen=" + craftsmen +
                ", orders=" + orders +
                ", places=" + places +
                '}';
    }
}