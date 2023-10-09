package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.Craftsman;

import java.util.List;

public interface ICraftsmanService {

    void addCraftsman(Craftsman craftsman);

    void removeCraftsmanById(long id);

    List<Craftsman> getCraftsmenByOrder(long id);

    List<Craftsman> getSortedAlphabetically();

    List<Craftsman> getSortedByBusyness();

    Craftsman getCraftsmanById(Long id);

    Craftsman createCraftsmen(long id, String name, String surname);

    List<Craftsman> getCraftsmen();

    void importCraftsmenFromCsv();

    void exportCraftsmenToCsv();

    void importCraftsmenFromJson();

    void exportCraftsmenToJson();
}
