package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.exception.NotFoundException;

import java.util.List;

public interface IGenericDao<T> {

    void create(T object);

    T findById(long id) throws NotFoundException;

    void update(T object);

    void delete(T object);

    List<T> getAll();
}
