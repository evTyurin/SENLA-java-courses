package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.fileHandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.fileHandlers.CsvWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CraftsmanDAO implements ICraftsmanDAO{

    private static CraftsmanDAO INSTANCE;
    private List<Craftsman> craftsmen;
    private CsvReader csvReader;
    private CsvWriter csvWriter;

    private CraftsmanDAO() {
        craftsmen = new ArrayList<>();
        csvReader = CsvReader.getInstance();
        csvWriter = CsvWriter.getInstance();
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

    @Override
    public List<Craftsman> importCraftsmen(String path) {
        return csvReader
                .readEntities(path)
                .stream()
                .map(entity -> {
                    String[] values = entity.split(",");
                    Craftsman craftsman = new Craftsman();
                    craftsman.setId(Long.parseLong(values[0]));
                    craftsman.setName(values[1]);
                    craftsman.setSurname(values[2]);
                    return craftsman;
                }).collect(Collectors.toList());
    }

    @Override
    public void exportCraftsmen(List<Craftsman> craftsmen, String path) {

        List<String> rawCraftsmen = craftsmen
                .stream()
                .map(Craftsman::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(rawCraftsmen, Constants.CRAFTSMEN_CSV_HEADER, path);
    }
}

