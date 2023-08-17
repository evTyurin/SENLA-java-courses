package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.util.fileHandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.fileHandlers.CsvWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GaragePlaceDAO implements IGaragePlaceDAO{

    private static GaragePlaceDAO INSTANCE;
    private List<GaragePlace> garagePlaces;
    private CsvReader cvsReader;
    private CsvWriter csvWriter;

    private GaragePlaceDAO() {
        garagePlaces = new ArrayList<>();
        cvsReader = CsvReader.getInstance();
        csvWriter = CsvWriter.getInstance();
    }

    public static GaragePlaceDAO getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GaragePlaceDAO();
        }
        return INSTANCE;
    }

    @Override
    public void addGaragePlace(GaragePlace garagePlace) {
        garagePlaces.add(garagePlace);
    }

    @Override
    public void deleteGaragePlace(GaragePlace garagePlace) {
        garagePlaces.remove(garagePlace);
    }

    @Override
    public List<GaragePlace> getGaragePlaces() {
        return garagePlaces;
    }

    @Override
    public List<String> importGaragePlaces(String path) {
        return cvsReader.readEntities(path);
    }

    @Override
    public void exportGaragePlaces(List<GaragePlace> garagePlaces, String path) {

        List<String> rawGaragePlaces = garagePlaces
                .stream()
                .map(GaragePlace::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(rawGaragePlaces, path);
    }
}
