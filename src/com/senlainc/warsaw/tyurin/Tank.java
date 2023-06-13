package com.senlainc.warsaw.tyurin;

public class Tank implements IProduct, IAssemblyLine{
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

    @Override
    public IProduct assembleProduct(IProduct product) {
        product.installFirstPart(body);
        product.installSecondPart(engine);
        product.installThirdPart(turret);
        return product;
    }
}
