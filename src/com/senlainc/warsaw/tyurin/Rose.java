package com.senlainc.warsaw.tyurin;

import java.util.Objects;

public class Rose extends Flower{
    public Rose(String name, double price) {
        super(name, price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), super.getPrice());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(!(obj instanceof Rose)) {
            return false;
        }
        Rose rose = (Rose) obj;
        return super.getPrice() == rose.getPrice() &&
                Objects.equals(super.getName(), rose.getName());
    }
}
