package com.senlainc.warsaw.tyurin;

public class TankAssemblyLine implements IAssemblyLine{
    private Body body;
    private Engine engine;
    private Turret turret;

    public TankAssemblyLine(Body body, Engine engine, Turret turret) {
        this.body = body;
        this.engine = engine;
        this.turret = turret;
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        product.installFirstPart(body);
        product.installSecondPart(engine);
        product.installThirdPart(turret);
        return product;
    }
}
