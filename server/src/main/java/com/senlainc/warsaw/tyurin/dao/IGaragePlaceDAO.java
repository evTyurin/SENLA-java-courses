package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;

import java.time.LocalDateTime;
import java.util.List;

public interface IGaragePlaceDAO {

    void addGaragePlace(GaragePlace garagePlace) throws Exception;

    void deleteGaragePlace(long id) throws Exception;

    List<GaragePlace> getGaragePlaces() throws Exception;

    GaragePlace getGaragePlace(long id) throws Exception;

    List<GaragePlace> getAvailableGaragePlaces() throws Exception;

    long getAvailablePlacesAmount(LocalDateTime localDateTime) throws Exception;
}
