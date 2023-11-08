package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Craftsman;

import java.util.List;

public interface ICraftsmanDAO {

    void addCraftsman(Craftsman craftsman) throws Exception;

    void deleteCraftsman(long id) throws Exception;

    List<Craftsman> getCraftsmen() throws Exception;

    Craftsman getCraftsman(long id) throws Exception;

    List<Craftsman> getCraftsmenByOrder(long id) throws Exception;

    List<Long> getCraftsmenIdByOrder(long id) throws Exception;

    List<Craftsman> getSortedAlphabetically() throws Exception;

    List<Craftsman> getSortedByBusyness() throws Exception;
}