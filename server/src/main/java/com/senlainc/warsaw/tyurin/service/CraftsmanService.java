package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.dao.ICraftsmanDAO;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvWriter;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonReader;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonWriter;
import org.apache.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DependencyClass
public class CraftsmanService implements ICraftsmanService{

    private final static Logger logger = Logger.getLogger(CraftsmanService.class);

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
    public void addCraftsman(Craftsman craftsman) throws Exception {
        craftsmanDAO.addCraftsman(craftsman);
    }

    @Override
    public void removeCraftsmanById(long id) throws Exception {
        craftsmanDAO.deleteCraftsman(id);
    }

    @Override
    public List<Craftsman> getCraftsmenByOrder(long id) throws Exception {
        return craftsmanDAO.getCraftsmenByOrder(id);
    }

    @Override
    public List<Craftsman> getSortedAlphabetically() throws Exception {
        return craftsmanDAO.getSortedAlphabetically();
    }

    @Override
    public List<Craftsman> getSortedByBusyness() throws Exception {
        return craftsmanDAO.getSortedByBusyness();
    }

    @Override
    public Craftsman getCraftsmanById(Long id) throws Exception {
        return craftsmanDAO.getCraftsman(id);
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
    public List<Craftsman> getCraftsmen() throws Exception {
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
                    Craftsman craftsman = null;
                    try {
                        craftsman = getCraftsmanById(importedCraftsman.getId());
                    } catch (Exception exception) {
                        logger.error("Can't get craftsman", exception);
                    }
                    if (craftsman == null) {
                        try {
                            craftsmanDAO.addCraftsman(importedCraftsman);
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
    public void exportCraftsmenToCsv() throws Exception {

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
                    Craftsman craftsman = null;
                    try {
                        craftsman = getCraftsmanById(importedCraftsman.getId());
                    } catch (Exception exception) {
                        logger.error("Can't get craftsman", exception);
                    }
                    if (craftsman == null) {
                        try {
                            craftsmanDAO.addCraftsman(importedCraftsman);
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
    public void exportCraftsmenToJson() throws Exception {
        jsonWriter.writeEntities(craftsmanDAO.getCraftsmen(), Constants.PATH_TO_CRAFTSMEN_JSON);
    }
}