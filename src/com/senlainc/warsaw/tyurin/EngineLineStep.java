package com.senlainc.warsaw.tyurin;

public class EngineLineStep implements ILineStep{

    @Override
    public IProductPart buildProductPart() {
        return new Engine();
    }
}
