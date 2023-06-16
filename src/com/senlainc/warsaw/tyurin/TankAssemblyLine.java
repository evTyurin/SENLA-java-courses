package com.senlainc.warsaw.tyurin;

public class TankAssemblyLine implements IAssemblyLine{
    private IProductPart body;
    private IProductPart engine;
    private IProductPart turret;

    public TankAssemblyLine(IProductPart body, IProductPart engine, IProductPart turret) {
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
