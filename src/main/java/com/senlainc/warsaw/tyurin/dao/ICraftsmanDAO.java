package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Craftsman;

import java.util.List;

public interface ICraftsmanDAO {

    void addCraftsman(Craftsman craftsman);

    void deleteCraftsman(Craftsman craftsman);

    List<Craftsman> getCraftsmen();

    List<Craftsman> importCraftsmenFromCsv(String path);

    void exportCraftsmenToCsv(List<Craftsman> craftsmen, String path);

    List<Craftsman> importCraftsmenFromJson(String path);

    void exportCraftsmenToJson(List<Craftsman> craftsmen, String path);
}