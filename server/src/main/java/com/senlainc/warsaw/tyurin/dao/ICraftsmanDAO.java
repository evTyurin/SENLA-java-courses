package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Craftsman;

import java.util.List;

public interface ICraftsmanDAO {

    void addCraftsman(Craftsman craftsman);

    void deleteCraftsman(Craftsman craftsman);

    List<Craftsman> getCraftsmen();
}