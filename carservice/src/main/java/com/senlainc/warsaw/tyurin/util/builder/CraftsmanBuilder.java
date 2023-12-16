package com.senlainc.warsaw.tyurin.util.builder;

import com.senlainc.warsaw.tyurin.dto.CraftsmanDto;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
public class CraftsmanBuilder {

    public CraftsmanBuilder() {
    }

    public Craftsman build(@Valid CraftsmanDto dto) {
        Craftsman craftsman = new Craftsman();
        craftsman.setName(dto.getName());
        craftsman.setSurname(dto.getSurname());
        craftsman.setId(dto.getId());
        return craftsman;
    }

    public CraftsmanDto build(Craftsman craftsman) {
        CraftsmanDto dto = new CraftsmanDto();
        dto.setName(craftsman.getName());
        dto.setSurname(craftsman.getSurname());
        dto.setId(craftsman.getId());
        return dto;
    }
}
