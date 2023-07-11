package com.senlainc.warsaw.tyurin.entity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Craftsman{

    private long id;
    private String name;
    private String surname;
    private Map<LocalDateTime, Boolean> schedule;

    public Craftsman() {
        schedule = new TreeMap<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
        Craftsman craftsman = (Craftsman) o;
        return id == craftsman.id &&
                name.equals(craftsman.name) &&
                surname.equals(craftsman.surname) &&
                Objects.equals(schedule, craftsman.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, schedule);
    }

    @Override
    public String toString() {
        return "Craftsman{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}
