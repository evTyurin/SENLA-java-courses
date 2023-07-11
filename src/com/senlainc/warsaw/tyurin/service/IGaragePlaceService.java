package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;

import java.time.LocalDateTime;
import java.util.List;

public interface IGaragePlaceService {

    void addGaragePlace(GaragePlace garagePlace);

    void deleteGaragePlace(GaragePlace garagePlace);

    List<GaragePlace> getAvailablePlaces();

    long getAvailablePlacesAmount(LocalDateTime localDateTime);

    LocalDateTime getNearestAvailableDate();
}

