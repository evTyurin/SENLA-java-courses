package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.GaragePlaceDto;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.service.IGaragePlaceService;
import com.senlainc.warsaw.tyurin.util.builder.GaragePlaceBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public GaragePlaceDto find(@PathVariable long id) {
        return garagePlaceBuilder.build(garagePlaceService.getGaragePlaceById(id));
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        garagePlaceService.removeGaragePlace(id);
    }

    @ResponseBody
    @GetMapping("nearest-available-date")
    public LocalDateTime getNearestAvailableDate() {
        return garagePlaceService.getNearestAvailableDate();
    }

    @ResponseBody
    @GetMapping("available-amount/{date}")
    public long getAvailablePlacesAmount(@PathVariable LocalDateTime date) {
        return garagePlaceService.getAvailablePlacesAmount(date);
    }

    @ResponseBody
    @GetMapping("available")
    public List<GaragePlaceDto> getAvailablePlaces() {
        return garagePlaceService
                .getAvailablePlaces()
                .stream()
                .map(garagePlaceBuilder::build)
                .collect(Collectors.toList());
    }
}
