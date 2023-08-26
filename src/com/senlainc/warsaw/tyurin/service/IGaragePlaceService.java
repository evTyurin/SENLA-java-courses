package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;

import java.time.LocalDateTime;
import java.util.List;

public interface IGaragePlaceService {

    void addGaragePlace(GaragePlace garagePlace);

    void removeGaragePlace(long id);

    List<GaragePlace> getAvailablePlaces();

    long getAvailablePlacesAmount(LocalDateTime localDateTime);

    LocalDateTime getNearestAvailableDate();

    GaragePlace createGaragePlace(long id, int number, double space);

    void importGaragePlaces();

    GaragePlace getGaragePlaceById(Long id);

    void exportGaragePlaces();
}

