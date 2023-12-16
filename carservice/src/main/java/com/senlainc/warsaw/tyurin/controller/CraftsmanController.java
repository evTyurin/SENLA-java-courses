package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.CraftsmanDto;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.service.ICraftsmanService;
import com.senlainc.warsaw.tyurin.util.builder.CraftsmanBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public Craftsman find(@PathVariable long id) {
        return craftsmanService.getCraftsmanById(id);
    }

    @ResponseBody
    @GetMapping("{id}/orders")
    public List<Craftsman> findByOrder(@PathVariable long id) {
        return craftsmanService.getCraftsmenByOrder(id);
    }

    @ResponseBody
    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        craftsmanService.removeCraftsmanById(id);
    }
}
