package com.senlainc.warsaw.tyurin.service;


import com.senlainc.warsaw.tyurin.dao.GarageDAO;
import com.senlainc.warsaw.tyurin.dao.IGarageDAO;
import com.senlainc.warsaw.tyurin.entity.Garage;

import java.util.List;

public class GarageService implements IGarageService{

    private static GarageService INSTANCE;
    private IGarageDAO garageDAO;

    private GarageService() {
        garageDAO = GarageDAO.getInstance();
    }

    public static GarageService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GarageService();
        }
        return INSTANCE;
    }

    @Override
    public List<Garage> getGarages() {
        return garageDAO.getGarages();
    }
}
