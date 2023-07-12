package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Garage;

import java.util.ArrayList;
import java.util.List;

public class GarageDAO implements IGarageDAO{

    private static GarageDAO INSTANCE;
    private List<Garage> garages;

    private GarageDAO() {
        garages = new ArrayList<>();
    }

    public static GarageDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GarageDAO();
        }
        return INSTANCE;
    }

    @Override
    public List<Garage> getGarages() {
        return garages;
    }
}