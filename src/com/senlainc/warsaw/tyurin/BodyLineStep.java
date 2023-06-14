package com.senlainc.warsaw.tyurin;

public class BodyLineStep implements ILineStep{

    @Override
    public IProductPart buildProductPart() {
        return new Body();
    }
}
