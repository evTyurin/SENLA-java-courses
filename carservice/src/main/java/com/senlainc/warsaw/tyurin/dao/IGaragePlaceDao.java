package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

public interface IGaragePlaceDao {

    void create(GaragePlace object);

    GaragePlace findById(long id) throws NotFoundException;

    void update(GaragePlace object);

    void delete(GaragePlace object);

    List<GaragePlace> getAll();

    List<GaragePlace> getAvailableGaragePlaces();

    long getAvailablePlacesAmount(LocalDateTime localDateTime);
}
