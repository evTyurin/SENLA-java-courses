package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.Craftsman;

import java.util.List;

public interface ICraftsmanService {

    void addCraftsman(Craftsman craftsman) throws Exception;

    void removeCraftsmanById(long id) throws Exception;

    List<Craftsman> getCraftsmenByOrder(long id) throws Exception;

    List<Craftsman> getSortedAlphabetically() throws Exception;

    List<Craftsman> getSortedByBusyness() throws Exception;

    Craftsman getCraftsmanById(Long id) throws Exception;

    Craftsman createCraftsmen(String name, String surname);

    List<Craftsman> getCraftsmen() throws Exception;

    void importCraftsmenFromCsv();

    void exportCraftsmenToCsv() throws Exception;

    void importCraftsmenFromJson();

    void exportCraftsmenToJson() throws Exception;
}
