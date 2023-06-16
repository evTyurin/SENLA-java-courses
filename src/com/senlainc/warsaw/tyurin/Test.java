package com.senlainc.warsaw.tyurin;

public class Test {

    public static void main(String[] args) {

        BodyLineStep bodyLineStep = new BodyLineStep();
        IProductPart body = bodyLineStep.buildProductPart();
        System.out.println("Created part of tank - body");

        EngineLineStep engineLineStep = new EngineLineStep();
        IProductPart engine = engineLineStep.buildProductPart();
        System.out.println("Created part of tank - engine");

        TurretLineStep turretLineStep = new TurretLineStep();
        IProductPart turret = turretLineStep.buildProductPart();
        System.out.println("All parts of tank have created");

        TankAssemblyLine tankAssemblyLine = new TankAssemblyLine(body, engine, turret);
        System.out.println("All parts of tank have installed");
        tankAssemblyLine.assembleProduct(new TankProduct());
        System.out.println("Tank created");
    }
}
