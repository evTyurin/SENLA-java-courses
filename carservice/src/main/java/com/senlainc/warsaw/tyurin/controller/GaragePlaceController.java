package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.GaragePlaceDto;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.service.IGaragePlaceService;
import com.senlainc.warsaw.tyurin.util.mapper.GaragePlaceMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("garage-places")
public class GaragePlaceController {

    private final GaragePlaceMapper garagePlaceMapper;
    private IGaragePlaceService garagePlaceService;

    public GaragePlaceController(GaragePlaceMapper garagePlaceMapper,
                                 IGaragePlaceService garagePlaceService) {
        this.garagePlaceMapper = garagePlaceMapper;
        this.garagePlaceService = garagePlaceService;
    }

    @PostMapping()
    public void create(@Valid @RequestBody GaragePlaceDto garagePlaceDto) {
        GaragePlace garagePlace = garagePlaceMapper.mapToEntity(garagePlaceDto);
        garagePlaceService.addGaragePlace(garagePlace);
    }

    @GetMapping("{id}")
    public GaragePlaceDto find(@PathVariable long id) throws NotFoundException {
        return garagePlaceMapper.mapToEntity(garagePlaceService.getGaragePlaceById(id));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) throws NotFoundException {
        garagePlaceService.removeGaragePlace(id);
    }

    @GetMapping("nearest-available-date")
    public LocalDateTime getNearestAvailableDate() {
        return garagePlaceService.getNearestAvailableDate();
    }

    @GetMapping("available-amount/{date}")
    public long getAvailablePlacesAmount(@PathVariable LocalDateTime date) {
        return garagePlaceService.getAvailablePlacesAmount(date);
    }

    @GetMapping("available")
    public List<GaragePlaceDto> getAvailablePlaces() {
        return garagePlaceService
                .getAvailablePlaces()
                .stream()
                .map(garagePlaceMapper::mapToEntity)
                .collect(Collectors.toList());
    }
}
