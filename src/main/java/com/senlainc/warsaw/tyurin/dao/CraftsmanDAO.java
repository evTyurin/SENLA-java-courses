package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Craftsman;

import java.util.ArrayList;
import java.util.List;

public class CraftsmanDAO implements ICraftsmanDAO{

    private static CraftsmanDAO INSTANCE;
    private List<Craftsman> craftsmen;

    private CraftsmanDAO() {
        craftsmen = new ArrayList<>();
    }

    public static CraftsmanDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CraftsmanDAO();
        }
        return INSTANCE;
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

