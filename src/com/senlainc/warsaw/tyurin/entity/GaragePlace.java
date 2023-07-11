package com.senlainc.warsaw.tyurin.entity;


import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class GaragePlace {

    private long id;
    private int number;
    private double space;
    private Map<LocalDateTime, Boolean> schedule;

    public GaragePlace() {
        schedule = new TreeMap<>();
    }

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

    public Map<LocalDateTime, Boolean> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<LocalDateTime, Boolean> schedule) {
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GaragePlace that = (GaragePlace) o;
        return id == that.id &&
                number == that.number &&
                Double.compare(that.space, space) == 0 &&
                Objects.equals(schedule, that.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, space, schedule);
    }

    @Override
    public String toString() {
        return "GaragePlace{" +
                "id=" + id +
                ", number=" + number +
                ", space=" + space +
                ", schedule=" + schedule +
                '}';
    }
}