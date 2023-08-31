package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;

import java.util.ArrayList;
import java.util.List;

public class GaragePlaceDAO implements IGaragePlaceDAO{

    private static GaragePlaceDAO INSTANCE;
    private List<GaragePlace> garagePlaces;

    private GaragePlaceDAO() {
        garagePlaces = new ArrayList<>();
    }

    public static GaragePlaceDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GaragePlaceDAO();
        }
        return INSTANCE;
    }

    @Override
    public void addGaragePlace(GaragePlace garagePlace) {
        garagePlaces.add(garagePlace);
    }

    @Override
    public void deleteGaragePlace(GaragePlace garagePlace) {
        garagePlaces.remove(garagePlace);
    }

    @Override
    public List<GaragePlace> getGaragePlaces() {
        return garagePlaces;
    }
}
