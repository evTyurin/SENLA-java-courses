package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;

import java.util.List;

public interface IGaragePlaceDAO {

    void addGaragePlace(GaragePlace garagePlace);

    void deleteGaragePlace(GaragePlace garagePlace);

    List<GaragePlace> getGaragePlaces();
}
