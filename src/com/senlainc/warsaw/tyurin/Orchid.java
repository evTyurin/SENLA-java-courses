package com.senlainc.warsaw.tyurin;

import java.util.Objects;

public class Orchid extends Flower{

    public Orchid(String name, double price) {
        super(name, price);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Orchid)) {
            return false;
        }
        Orchid orchid = (Orchid) obj;
        return super.getPrice() == orchid.getPrice() &&
                Objects.equals(super.getName(), orchid.getName());
    }
}
