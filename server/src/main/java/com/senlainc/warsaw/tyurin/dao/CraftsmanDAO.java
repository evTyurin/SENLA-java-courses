package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.entity.Craftsman;

import java.util.ArrayList;
import java.util.List;

@DependencyClass
public class CraftsmanDAO implements ICraftsmanDAO{

    private static CraftsmanDAO INSTANCE;
    private List<Craftsman> craftsmen;

    public CraftsmanDAO() {
        craftsmen = new ArrayList<>();
    }

    public static CraftsmanDAO getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void addCraftsman(Craftsman craftsman) {
        craftsmen.add(craftsman);
    }

    @Override
    public void deleteCraftsman(Craftsman craftsman) {
        craftsmen.remove(craftsman);
    }

    @Override
    public List<Craftsman> getCraftsmen() {
        return craftsmen;
    }
}

