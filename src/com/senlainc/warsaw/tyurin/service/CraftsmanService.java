package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.CraftsmanDAO;
import com.senlainc.warsaw.tyurin.dao.ICraftsmanDAO;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CraftsmanService implements ICraftsmanService{

    private static CraftsmanService INSTANCE;
    private ICraftsmanDAO craftsmanStorage;

    private CraftsmanService() {
        craftsmanStorage = CraftsmanDAO.getInstance();
    }

    public static CraftsmanService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CraftsmanService();
        }
        return INSTANCE;
    }


    @Override
    public void addCraftsman(Craftsman craftsman) {
        craftsmanStorage
                .getCraftsmen()
                .add(craftsman);
    }

    @Override
    public void removeCraftsmen(Craftsman craftsman) {
        craftsmanStorage
                .getCraftsmen()
                .remove(craftsman);
    }

    @Override
    public List<Craftsman> getCraftsmenByOrder(Order order) {
        return order.getCraftsmen();
    }

    @Override
    public List<Craftsman> getSortedAlphabetically() {
        return craftsmanStorage
                .getCraftsmen()
                .stream()
                .sorted(Comparator
                        .comparing(craftsman -> ((Craftsman)craftsman)
                                .getSurname())
                        .thenComparing(craftsman -> ((Craftsman)craftsman)
                                .getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Craftsman> getSortedByBusyness() {
        List<Craftsman> craftsmen = new ArrayList<>(craftsmanStorage.getCraftsmen());

        craftsmen.sort(Comparator.comparing(craftsman -> craftsman
                .getSchedule()
                .entrySet()
                .stream()
                .filter(Map.Entry::getValue)
                .count()));

        return craftsmen;
    }
}