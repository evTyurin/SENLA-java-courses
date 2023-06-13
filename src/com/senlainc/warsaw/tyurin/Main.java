package com.senlainc.warsaw.tyurin;

public class Main {
    public static void main(String[] args) {

        Bouquet bouquet = new Bouquet();

        System.out.println("Created bouquet of flowers");
        bouquet.addFlower(new Rose("rose", 5.5));
        bouquet.addFlower(new Rose("rose", 5.5));
        bouquet.addFlower(new Aster("aster", 7.8));
        bouquet.addFlower(new Orchid("orchid", 9.0));
        bouquet.addFlower(new Orchid("orchid", 9.0));

        System.out.println("Determine cost of bouquet");
        bouquet.determineBouquetCost(bouquet.getBouquet());
        System.out.println("Cost of bouquet is " + bouquet.getPrice());
    }
}