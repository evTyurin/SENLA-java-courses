package com.senlainc.warsaw.tyurin;

public class Body implements ILineStep, IProductPart{

    @Override
    public IProductPart buildProductPart() {
        return new Body();
    }
}
