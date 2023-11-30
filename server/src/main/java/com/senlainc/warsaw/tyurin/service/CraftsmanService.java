package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.ICraftsmanDao;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.csvhandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.csvhandlers.CsvWriter;
import com.senlainc.warsaw.tyurin.util.jsonhandlers.JsonReader;
import com.senlainc.warsaw.tyurin.util.jsonhandlers.JsonWriter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CraftsmanService implements ICraftsmanService{

    private final static Logger logger = Logger.getLogger(CraftsmanService.class);

    @Autowired
    private ICraftsmanDao craftsmanDao;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private CsvReader csvReader;
    @Autowired
    private CsvWriter csvWriter;
    @Autowired
    private JsonReader jsonReader;
    @Autowired
    private JsonWriter jsonWriter;

    @Override
    public void addCraftsman(Craftsman craftsman) {
        craftsmanDao.create(craftsman);
    }

    @Override
    public void removeCraftsmanById(long id) {
        craftsmanDao.delete(craftsmanDao.findById(id));
    }

    @Override
    public List<Craftsman> getCraftsmenByOrder(long id) {
        return craftsmanDao.getCraftsmenByOrder(id);
    }

    @Override
    public List<Craftsman> getSortedAlphabetically() {
        return craftsmanDao.getSortedAlphabetically();
    }

    @Override
    public List<Craftsman> getSortedByBusyness() {
        return craftsmanDao.getSortedByBusyness();
    }

    @Override
    public Craftsman getCraftsmanById(Long id) {
        return craftsmanDao.findById(id);
    }

    @Override
    public Craftsman createCraftsmen(String name, String surname) {
        Craftsman craftsman = new Craftsman();
        craftsman.setName(name);
        craftsman.setSurname(surname);
        System.out.println("craftsmen was created");
        return craftsman;
    }

    @Override
    public List<Craftsman> getCraftsmen() {
        return craftsmanDao.getAll();
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
                    Craftsman craftsman = null;
                    try {
                        craftsman = getCraftsmanById(importedCraftsman.getId());
                    } catch (Exception exception) {
                        logger.error("Can't get craftsman", exception);
                    }
                    if (craftsman == null) {
                        try {
                            craftsmanDao.create(importedCraftsman);
                        } catch (Exception exception) {
                            logger.error("Can't add craftsman", exception);
                        }
                    } else if (!craftsman.equals(importedCraftsman)) {
                        craftsman.setSurname(importedCraftsman.getSurname());
                        craftsman.setName(importedCraftsman.getName());
                    }
                });
    }

    @Override
    public void exportCraftsmenToCsv() {

        List<String> craftsmen = craftsmanDao
                .getAll()
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
                    Craftsman craftsman = null;
                    try {
                        craftsman = getCraftsmanById(importedCraftsman.getId());
                    } catch (Exception exception) {
                        logger.error("Can't get craftsman", exception);
                    }
                    if (craftsman == null) {
                        try {
                            craftsmanDao.create(importedCraftsman);
                        } catch (Exception exception) {
                            logger.error("Can't add craftsman", exception);
                        }
                    } else if (!craftsman.equals(importedCraftsman)) {
                        craftsman.setSurname(importedCraftsman.getSurname());
                        craftsman.setName(importedCraftsman.getName());
                    }
                });
    }

    @Override
    public void exportCraftsmenToJson() {
        jsonWriter.writeEntities(craftsmanDao.getAll(), Constants.PATH_TO_CRAFTSMEN_JSON);
    }
}