package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.GaragePlaceDto;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.service.IGaragePlaceService;
import com.senlainc.warsaw.tyurin.util.builder.GaragePlaceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("garage-places")
public class GaragePlaceController {

    private final GaragePlaceBuilder garagePlaceBuilder;
    private IGaragePlaceService garagePlaceService;

    public GaragePlaceController(GaragePlaceBuilder garagePlaceBuilder,
                                 IGaragePlaceService garagePlaceService) {
        this.garagePlaceBuilder = garagePlaceBuilder;
        this.garagePlaceService = garagePlaceService;
    }

    @ResponseBody
    @PostMapping()
    public void create(@Valid @RequestBody GaragePlaceDto garagePlaceDto) {
        GaragePlace garagePlace = garagePlaceBuilder.build(garagePlaceDto);
        garagePlaceService.addGaragePlace(garagePlace);
    }

    @ResponseBody
    @GetMapping("{id}")
    public GaragePlace find(@PathVariable long id) {
        return garagePlaceService.getGaragePlaceById(id);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        garagePlaceService.removeGaragePlace(id);
    }
}
