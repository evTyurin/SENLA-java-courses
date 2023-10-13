package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;

import java.util.ArrayList;
import java.util.List;

@DependencyClass
public class GaragePlaceDAO implements IGaragePlaceDAO{

    private static GaragePlaceDAO INSTANCE;
    private List<GaragePlace> garagePlaces;

    public GaragePlaceDAO() {
        garagePlaces = new ArrayList<>();
    }

    public static GaragePlaceDAO getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
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
