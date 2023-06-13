package com.senlainc.warsaw.tyurin;

import java.util.ArrayList;
import java.util.List;

public class Garage {

    private Garage() {
        craftsmen = new ArrayList<>();
        orders = new ArrayList<>();
    }

    private static Garage INSTANCE;
    private double space;
    private List<Craftsman> craftsmen;
    private List<Order> orders;

    public static Garage getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Garage();
        }
        return INSTANCE;
    }

    public void addSpace(double space) {
        this.space += space;
    }

    public void deleteSpace(double space) {
        this.space -= space;
    }

    public void addCraftsman(Craftsman craftsman) {
        craftsmen.add(craftsman);
    }

    public void removeCraftsman(Craftsman craftsman) {
        craftsmen.remove(craftsman);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void deleteOrder(Order order) {
        orders.remove(order);
    }

}
