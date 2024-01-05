package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface IGaragePlaceService {

    void addGaragePlace(GaragePlace garagePlace);

    void removeGaragePlace(long id) throws NotFoundException;

    List<GaragePlace> getAvailablePlaces();

    long getAvailablePlacesAmount(LocalDateTime localDateTime);

    LocalDateTime getNearestAvailableDate();

    GaragePlace getGaragePlaceById(Long id) throws NotFoundException;
}
