package com.senlainc.warsaw.tyurin.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class GaragePlaceDto implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private int number;
    private double space;

    public long getId() {
        return id;
    }

    @JsonIgnore
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
    public String toString() {
        return "GaragePlaceDto{" +
                "id=" + id +
                ", number=" + number +
                ", space=" + space +
                '}';
    }
}
