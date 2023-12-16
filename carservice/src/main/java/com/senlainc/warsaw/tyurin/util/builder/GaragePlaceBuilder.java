package com.senlainc.warsaw.tyurin.util.builder;

import com.senlainc.warsaw.tyurin.dto.GaragePlaceDto;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class GaragePlaceBuilder {

    public GaragePlace build(@Valid GaragePlaceDto dto) {
        GaragePlace garagePlace = new GaragePlace();
        garagePlace.setNumber(dto.getNumber());
        garagePlace.setSpace(dto.getSpace());
        return garagePlace;
    }

    public GaragePlaceDto build(GaragePlace garagePlace) {
        GaragePlaceDto dto = new GaragePlaceDto();
        dto.setSpace(garagePlace.getSpace());
        dto.setNumber(garagePlace.getNumber());
        dto.setId(garagePlace.getId());
        return dto;
    }
}
