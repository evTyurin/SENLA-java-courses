package com.senlainc.warsaw.tyurin;

public class Turret implements ILineStep, IProductPart{

    @Override
    public IProductPart buildProductPart() {
        return new Turret();
    }
}
