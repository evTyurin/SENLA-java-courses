package com.senlainc.warsaw.tyurin;

public class Engine implements ILineStep, IProductPart{

    @Override
    public IProductPart buildProductPart() {
        return new Engine();
    }
}
