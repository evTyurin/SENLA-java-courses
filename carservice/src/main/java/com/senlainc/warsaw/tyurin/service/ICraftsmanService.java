package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.exception.ExpectationFailedException;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;

import java.util.List;

public interface ICraftsmanService {

    void addCraftsman(Craftsman craftsman);

    void removeCraftsmanById(long id) throws NotFoundException;

    List<Craftsman> getCraftsmenByOrder(long id);

    List<Craftsman> getSortedAlphabetically();

    List<Craftsman> getSortedByBusyness();

    Craftsman getCraftsmanById(Long id) throws NotFoundException;

    Craftsman createCraftsmen(String name, String surname);

    List<Craftsman> getCraftsmen();

    List<Craftsman> getSortedByCriteria(String criteria) throws ExpectationFailedException;
}
