package com.senlainc.warsaw.tyurin;

public class TankProduct implements IProduct{
    private IProductPart body;
    private IProductPart engine;
    private IProductPart turret;

    @Override
    public void installFirstPart(IProductPart productPart) {
        this.body = productPart;
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        this.engine = productPart;
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        this.turret = productPart;
    }
}
