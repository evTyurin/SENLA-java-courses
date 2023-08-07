package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.Order;

import java.util.List;

public interface ICraftsmanService {

    void addCraftsman(Craftsman craftsman);

    void removeCraftsmanById(long id);

    List<Craftsman> getCraftsmenByOrder(long id);

    List<Craftsman> getSortedAlphabetically();

    List<Craftsman> getSortedByBusyness();

    Craftsman getCraftsmanById(Long id);

    Craftsman createCraftsmen(String data);

    List<Craftsman> getCraftsmen();
}
