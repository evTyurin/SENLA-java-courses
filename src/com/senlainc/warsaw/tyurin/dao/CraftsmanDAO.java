package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Craftsman;
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
    public List<String> importCraftsmen(String path) {
        return csvReader.readEntities(path);
    }

    @Override
    public void exportCraftsmen(List<Craftsman> craftsmen, String path) {

        List<String> rawCraftsmen = craftsmen
                .stream()
                .map(Craftsman::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(rawCraftsmen, path);
    }
}

