package com.senlainc.warsaw.tyurin;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

public class Bouquet {
    private Map<Flower, Integer> bouquet;
    private double price;

    public Bouquet() {
        this.bouquet = new HashMap<>();
    }

    public Map<Flower, Integer> getBouquet() {
        return bouquet;
    }

    public void setBouquet(Map<Flower, Integer> bouquet) {
        this.bouquet = bouquet;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void determineBouquetCost(Map<Flower, Integer> bouquet) {
        this.price = bouquet.entrySet().stream().map(entry ->
                new SimpleEntry<>(entry.getKey(), entry.getKey().getPrice() * entry.getValue()))
                .mapToDouble(SimpleEntry::getValue).sum();
    }

    public void addFlower(Flower flower) {
        bouquet.merge(flower, 1, Integer::sum);
    }
}
