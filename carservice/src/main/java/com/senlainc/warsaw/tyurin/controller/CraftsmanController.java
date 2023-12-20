package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.CraftsmanDto;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.exception.ExpectationFailedException;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.service.ICraftsmanService;
import com.senlainc.warsaw.tyurin.util.mapper.CraftsmanMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("craftsmen")
public class CraftsmanController {

    private final CraftsmanMapper craftsmanMapper;
    private ICraftsmanService craftsmanService;

    public CraftsmanController(CraftsmanMapper craftsmanMapper,
                               ICraftsmanService craftsmanService) {
        this.craftsmanMapper = craftsmanMapper;
        this.craftsmanService = craftsmanService;
    }

    @PostMapping()
    public void create(@Valid @RequestBody CraftsmanDto craftsmanDto) {
        Craftsman craftsman = craftsmanMapper.mapToEntity(craftsmanDto);
        craftsmanService.addCraftsman(craftsman);
    }

    @GetMapping("{id}")
    public CraftsmanDto find(@PathVariable long id) throws NotFoundException {
        return craftsmanMapper.mapToEntity(craftsmanService.getCraftsmanById(id));
    }

    @GetMapping("{id}/orders")
    public List<CraftsmanDto> findByOrder(@PathVariable long id) {
        return craftsmanService
                .getCraftsmenByOrder(id)
                .stream()
                .map(craftsmanMapper::mapToEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("sort/{criteria}")
    public List<CraftsmanDto> getSortedByCriteria(@PathVariable String criteria) throws ExpectationFailedException {
        return craftsmanService
                .getSortedByCriteria(criteria)
                .stream()
                .map(craftsmanMapper::mapToEntity)
                .collect(Collectors.toList());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) throws NotFoundException {
        craftsmanService.removeCraftsmanById(id);
    }
}
