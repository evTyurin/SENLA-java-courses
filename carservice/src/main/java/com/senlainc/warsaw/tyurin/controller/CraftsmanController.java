package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.CraftsmanDto;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.exception.CriteriaNotFoundException;
import com.senlainc.warsaw.tyurin.service.ICraftsmanService;
import com.senlainc.warsaw.tyurin.util.builder.CraftsmanBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("craftsmen")
public class CraftsmanController {

    private final CraftsmanBuilder craftsmanBuilder;
    private ICraftsmanService craftsmanService;

    public CraftsmanController(CraftsmanBuilder craftsmanBuilder,
                               ICraftsmanService craftsmanService) {
        this.craftsmanBuilder = craftsmanBuilder;
        this.craftsmanService = craftsmanService;
    }

    @ResponseBody
    @PostMapping()
    public void create(@Valid @RequestBody CraftsmanDto craftsmanDto) {
        Craftsman craftsman = craftsmanBuilder.build(craftsmanDto);
        craftsmanService.addCraftsman(craftsman);
    }

    @ResponseBody
    @GetMapping("{id}")
    public CraftsmanDto find(@PathVariable long id) {
        return craftsmanBuilder.build(craftsmanService.getCraftsmanById(id));
    }

    @ResponseBody
    @GetMapping("{id}/orders")
    public List<CraftsmanDto> findByOrder(@PathVariable long id) {
        return craftsmanService
                .getCraftsmenByOrder(id)
                .stream()
                .map(craftsman -> craftsmanBuilder.build(craftsman))
                .collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("sort/{sortCriteria}")
    public List<CraftsmanDto> getSortedByCriteria(@PathVariable String criteria) throws CriteriaNotFoundException {
        if(criteria.equals("business")) {
            return craftsmanService
                    .getSortedByBusyness()
                    .stream()
                    .map(craftsman -> craftsmanBuilder.build(craftsman))
                    .collect(Collectors.toList());
        } else if (criteria.equals("alphabetically")) {
            return craftsmanService
                    .getSortedAlphabetically()
                    .stream()
                    .map(craftsman -> craftsmanBuilder.build(craftsman))
                    .collect(Collectors.toList());
        }
        throw new CriteriaNotFoundException("criteria " + criteria + " not available", 405);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        craftsmanService.removeCraftsmanById(id);
    }
}
