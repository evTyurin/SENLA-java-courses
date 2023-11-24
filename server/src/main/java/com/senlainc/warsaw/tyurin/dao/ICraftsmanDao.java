package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Craftsman;

import java.util.List;

public interface ICraftsmanDao {

    void create(Craftsman craftsman);

    Craftsman findById(long id);

    void update(Craftsman object);

    void delete(Craftsman object);

    List<Craftsman> getAll();

    List<Craftsman> getCraftsmenByOrder(long id);

    List<Craftsman> getSortedAlphabetically();

    List<Craftsman> getSortedByBusyness();
}