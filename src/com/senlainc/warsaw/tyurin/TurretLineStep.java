package com.senlainc.warsaw.tyurin;

public class TurretLineStep implements ILineStep{

    @Override
    public IProductPart buildProductPart() {
        return new Turret();
    }
}
