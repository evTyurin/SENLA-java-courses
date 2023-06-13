package com.senlainc.warsaw.tyurin;

import java.util.Objects;

public class Aster extends Flower{
    public Aster(String name, double price) {
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
        if(!(obj instanceof Aster)) {
            return false;
        }
        Aster aster = (Aster) obj;
        return super.getPrice() == aster.getPrice() &&
                Objects.equals(super.getName(), aster.getName());
    }
}
