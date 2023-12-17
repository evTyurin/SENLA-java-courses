package com.senlainc.warsaw.tyurin.dto;

import java.io.Serializable;

public class GaragePlaceDto implements Serializable {

    private long id;
    private int number;
    private double space;

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
}
