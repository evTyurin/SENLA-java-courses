package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;

import java.time.LocalDateTime;
import java.util.List;

public interface IGaragePlaceService {

    void addGaragePlace(GaragePlace garagePlace) throws Exception;

    void removeGaragePlace(long id) throws Exception;

    List<GaragePlace> getAvailablePlaces() throws Exception;

    long getAvailablePlacesAmount(LocalDateTime localDateTime) throws Exception;

    LocalDateTime getNearestAvailableDate() throws Exception;

    GaragePlace createGaragePlace(int number, double space);

    GaragePlace getGaragePlaceById(Long id) throws Exception;

    void importGaragePlacesFromCsv();

    void exportGaragePlacesToCsv() throws Exception;

    void importGaragePlacesFromJson();

    void exportGaragePlacesToJson() throws Exception;
}

