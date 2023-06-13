package com.senlainc.warsaw.tyurin;

public class Test {

    public static void main(String[] args) {
        Body body = new Body();
        body.buildProductPart();
        System.out.println("Created part of tank - body");

        Engine engine = new Engine();
        engine.buildProductPart();
        System.out.println("Created part of tank - engine");

        Turret turret = new Turret();
        turret.buildProductPart();
        System.out.println("Created part of tank - turret");

	    Tank tank = new Tank();
        tank.installFirstPart(body);
        tank.installSecondPart(engine);
        tank.installThirdPart(turret);
        System.out.println("All parts of tank are installed");

        tank.assembleProduct(new Tank());
        System.out.println("Tank is ready to move!!!");
    }
}
