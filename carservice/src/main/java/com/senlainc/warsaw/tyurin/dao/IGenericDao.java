package com.senlainc.warsaw.tyurin.dao;

import java.util.List;

public interface IGenericDao<T> {

    void create(T object);

    T findById(long id);

    void update(T object);

    void delete(T object);

    List<T> getAll();
}
