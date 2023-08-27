package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvWriter;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonReader;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GaragePlaceDAO implements IGaragePlaceDAO{

    private static GaragePlaceDAO INSTANCE;
    private List<GaragePlace> garagePlaces;
    private CsvReader csvReader;
    private CsvWriter csvWriter;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private GaragePlaceDAO() {
        garagePlaces = new ArrayList<>();
        csvReader = CsvReader.getInstance();
        csvWriter = CsvWriter.getInstance();
        jsonReader = JsonReader.getInstance();
        jsonWriter = JsonWriter.getInstance();
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
    public List<GaragePlace> importGaragePlacesFromCsv(String path) {

        return csvReader
                .readEntities(path)
                .stream()
                .map(entity -> {
                    String[] values = entity.split(",");
                    GaragePlace garagePlace = new GaragePlace();
                    garagePlace.setId(Long.parseLong(values[0]));
                    garagePlace.setNumber(Integer.parseInt(values[1]));
                    garagePlace.setSpace(Double.parseDouble(values[2]));
                    return garagePlace;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void exportGaragePlacesToCsv(List<GaragePlace> garagePlaces, String path) {

        List<String> rawGaragePlaces = garagePlaces
                .stream()
                .map(GaragePlace::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(rawGaragePlaces, Constants.GARAGE_PLACES_CSV_HEADER, path);
    }

    @Override
    public List<GaragePlace> importGaragePlacesFromJson(String path) {
        return jsonReader.readEntities(GaragePlace.class, path);
    }

    @Override
    public void exportGaragePlacesToJson(List<GaragePlace> garagePlaces, String path) {
        jsonWriter.writeEntities(garagePlaces,path);
    }
}
