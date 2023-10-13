package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.dao.ICraftsmanDAO;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.OrderStatus;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvWriter;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonReader;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonWriter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DependencyClass
public class CraftsmanService implements ICraftsmanService{

    private static CraftsmanService INSTANCE;
    @DependencyComponent
    private ICraftsmanDAO craftsmanDAO;
    @DependencyComponent
    private IOrderService orderService;
    @DependencyComponent
    private CsvReader csvReader;
    @DependencyComponent
    private CsvWriter csvWriter;
    @DependencyComponent
    private JsonReader jsonReader;
    @DependencyComponent
    private JsonWriter jsonWriter;

    public static CraftsmanService getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void addCraftsman(Craftsman craftsman) {
        craftsmanDAO.addCraftsman(craftsman);
    }

    @Override
    public void removeCraftsmanById(long id) {

        craftsmanDAO
                .getCraftsmen()
                .remove(craftsmanDAO
                        .getCraftsmen()
                        .stream()
                        .filter(craftsman -> craftsman.getId() == id)
                        .findFirst()
                        .orElse(null));
    }

    @Override
    public List<Craftsman> getCraftsmenByOrder(long id) {
        List<Craftsman> craftsmen = new ArrayList<>();

        orderService
                .getOrderById(id)
                .getCraftsmenId()
                .forEach(craftsmanId -> craftsmen.add(getCraftsmanById(craftsmanId)));

        return craftsmen;
    }

    @Override
    public List<Craftsman> getSortedAlphabetically() {
        return craftsmanDAO
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
        Map<Long, Integer> craftsmenBusiness = new HashMap<>();
        List<Craftsman> sortedCraftsmen = new ArrayList<>();

        orderService
                .getOrders()
                .stream()
                .filter(order -> ((!order
                        .getOrderStatus()
                        .equals(OrderStatus.CANCELED))))
                .forEach(order -> order
                                .getCraftsmenId()
                                .forEach(id -> {
                            if (craftsmenBusiness.containsKey(id)) {
                                craftsmenBusiness.replace(id, craftsmenBusiness.get(id) + 1);
                            } else {
                                craftsmenBusiness.put(id, 1);
                            }
                        }));

        craftsmenBusiness
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(id -> {
            sortedCraftsmen.add(getCraftsmanById(id.getKey()));
        });

        return sortedCraftsmen;
    }

    @Override
    public Craftsman getCraftsmanById(Long id) {

        return craftsmanDAO
                .getCraftsmen()
                .stream()
                .filter(craftsman -> craftsman.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Craftsman createCraftsmen(long id, String name, String surname) {
        Craftsman craftsman = new Craftsman();
        craftsman.setId(id);
        craftsman.setName(name);
        craftsman.setSurname(surname);
        System.out.println("craftsmen was created");
        return craftsman;
    }

    @Override
    public List<Craftsman> getCraftsmen() {
        return craftsmanDAO.getCraftsmen();
    }

    @Override
    public void importCraftsmenFromCsv() {

        csvReader
                .readEntities(Constants.PATH_TO_CRAFTSMEN_CSV)
                .stream()
                .map(entity -> {
                    String[] values = entity.split(",");
                    Craftsman craftsman = new Craftsman();
                    craftsman.setId(Long.parseLong(values[0]));
                    craftsman.setName(values[1]);
                    craftsman.setSurname(values[2]);
                    return craftsman;
                })
                .forEach(importedCraftsman -> {
                    Craftsman craftsman = getCraftsmanById(importedCraftsman.getId());
                    if (craftsman == null) {
                        craftsmanDAO.addCraftsman(importedCraftsman);
                    } else if (!craftsman.equals(importedCraftsman)) {
                        craftsman.setSurname(importedCraftsman.getSurname());
                        craftsman.setName(importedCraftsman.getName());
                    }
                });
    }

    @Override
    public void exportCraftsmenToCsv() {

        List<String> craftsmen = craftsmanDAO
                .getCraftsmen()
                .stream()
                .sorted(Comparator.comparing(Craftsman::getId))
                .map(Craftsman::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(craftsmen,
                Constants.CRAFTSMEN_CSV_HEADER,
                Constants.PATH_TO_CRAFTSMEN_CSV);
    }

    @Override
    public void importCraftsmenFromJson() {

        jsonReader.readEntities(Craftsman.class, Constants.PATH_TO_CRAFTSMEN_JSON)
                .forEach(importedCraftsman -> {
                    Craftsman craftsman = getCraftsmanById(importedCraftsman.getId());
                    if (craftsman == null) {
                        craftsmanDAO.addCraftsman(importedCraftsman);
                    } else if (!craftsman.equals(importedCraftsman)) {
                        craftsman.setSurname(importedCraftsman.getSurname());
                        craftsman.setName(importedCraftsman.getName());
                    }
                });
    }

    @Override
    public void exportCraftsmenToJson() {
        jsonWriter.writeEntities(craftsmanDAO.getCraftsmen(), Constants.PATH_TO_CRAFTSMEN_JSON);
    }
}