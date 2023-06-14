package com.senlainc.warsaw.tyurin;

public class Test {

    public static void main(String[] args) {

        BodyLineStep bodyLineStep = new BodyLineStep();
        Body body = (Body) bodyLineStep.buildProductPart();
        System.out.println("Created part of tank - body");

        EngineLineStep engineLineStep = new EngineLineStep();
        Engine engine = (Engine) engineLineStep.buildProductPart();
        System.out.println("Created part of tank - engine");

        TurretLineStep turretLineStep = new TurretLineStep();
        Turret turret = (Turret) turretLineStep.buildProductPart();
        System.out.println("All parts of tank have created");

        TankAssemblyLine tankAssemblyLine = new TankAssemblyLine(body, engine, turret);
        System.out.println("All parts of tank have installed");
        tankAssemblyLine.assembleProduct(new TankProduct());
        System.out.println("Tank created");
    }
}
