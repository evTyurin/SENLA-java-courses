package com.senlainc.warsaw.tyurin;

public class TankProduct implements IProduct{
    private Body body;
    private Engine engine;
    private Turret turret;

    @Override
    public void installFirstPart(IProductPart productPart) {
        this.body = (Body) productPart;
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        this.engine = (Engine) productPart;
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        this.turret = (Turret) productPart;
    }
}
