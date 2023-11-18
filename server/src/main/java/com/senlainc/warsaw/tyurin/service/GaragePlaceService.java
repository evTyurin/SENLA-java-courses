package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.annotation.ConfigProperty;
import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyComponent;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.dao.IGaragePlaceDao;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.csvhandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.csvhandlers.CsvWriter;
import com.senlainc.warsaw.tyurin.util.jsonhandlers.JsonReader;
import com.senlainc.warsaw.tyurin.util.jsonhandlers.JsonWriter;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@DependencyClass
public class GaragePlaceService implements IGaragePlaceService{

    private final static Logger logger = Logger.getLogger(GaragePlaceService.class);

    private static GaragePlaceService INSTANCE;
    @DependencyComponent
    private IGaragePlaceDao garagePlaceDao;
    @DependencyComponent
    private IOrderService orderService;
    @DependencyComponent
    private ICraftsmanService craftsmanService;
    @DependencyComponent
    private CsvReader csvReader;
    @DependencyComponent
    private CsvWriter csvWriter;
    @DependencyComponent
    private JsonReader jsonReader;
    @DependencyComponent
    private JsonWriter jsonWriter;
    @ConfigProperty(propertyKey = Constants.ABILITY_TO_ADD_GARAGE_PLACE)
    private boolean isGaragePlaceAddable;
    @ConfigProperty(propertyKey = Constants.ABILITY_TO_REMOVE_GARAGE_PLACE)
    private boolean isGaragePlaceRemovable;

    public static GaragePlaceService getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
    }

    @Override
    public void addGaragePlace(GaragePlace garagePlace) throws Exception {

        if (isGaragePlaceAddable) {
            garagePlaceDao.addGaragePlace(garagePlace);
        } else {
            logger.error("Adding garage places was prohibited");
        }
    }

    @Override
    public void removeGaragePlace(long id) throws Exception {

        if (isGaragePlaceRemovable) {
            garagePlaceDao.deleteGaragePlace(id);
        } else {
            logger.error("Removing garage places was prohibited");
        }
    }

    @Override
    public List<GaragePlace> getAvailablePlaces() throws Exception {
        return garagePlaceDao.getAvailableGaragePlaces();
    }

    @Override
    public long getAvailablePlacesAmount(LocalDateTime localDateTime) throws Exception {
       return garagePlaceDao.getAvailablePlacesAmount(localDateTime);
    }

    @Override
    public LocalDateTime getNearestAvailableDate() throws Exception {
        LocalDateTime currantTime = LocalDateTime.now();

        LocalDateTime searchTime = currantTime
                .minusMinutes(currantTime.getMinute())
                .minusSeconds(currantTime.getSecond())
                .minusNanos(currantTime.getNano())
                .plusHours(1);

        if (getAvailablePlacesAmount(searchTime) > 0) {
            return searchTime;
        }
        List<Order> ordersInProgress = orderService.getArchivedOrdersSortedByCompletionDate();
        for (Order order : ordersInProgress) {
            if (getAvailablePlacesAmount(order.getCompletionDate()) > 0) {
                return order.getCompletionDate();
            }
        }
        return null;
    }

    @Override
    public GaragePlace createGaragePlace(int number, double space) {
        GaragePlace garagePlace = new GaragePlace();
        garagePlace.setNumber(number);
        garagePlace.setSpace(space);
        return garagePlace;
    }

    @Override
    public void importGaragePlacesFromCsv() {

        csvReader
                .readEntities(Constants.PATH_TO_GARAGE_PLACES_CSV)
                .stream()
                .map(entity -> {
                    String[] values = entity.split(",");
                    GaragePlace garagePlace = new GaragePlace();
                    garagePlace.setId(Long.parseLong(values[0]));
                    garagePlace.setNumber(Integer.parseInt(values[1]));
                    garagePlace.setSpace(Double.parseDouble(values[2]));
                    return garagePlace;
                })
                .forEach(importGaragePlace -> {
                    GaragePlace garagePlace = null;
                    try {
                        garagePlace = getGaragePlaceById(importGaragePlace.getId());
                    } catch (Exception exception) {
                        logger.error("Can't get garage place", exception);
                    }
                    if (garagePlace == null) {
                        try {
                            garagePlaceDao.addGaragePlace(importGaragePlace);
                        } catch (Exception exception) {
                            logger.error("Can't add garage place", exception);
                        }
                    } else if (!garagePlace.equals(importGaragePlace)) {
                        garagePlace.setNumber(importGaragePlace.getNumber());
                        garagePlace.setSpace(importGaragePlace.getSpace());
                    }
                });
    }

    @Override
    public GaragePlace getGaragePlaceById(Long id) throws Exception {
        return garagePlaceDao.getGaragePlace(id);
    }

    @Override
    public void exportGaragePlacesToCsv() throws Exception {

        List<String> garagePlaces = garagePlaceDao
                .getGaragePlaces()
                .stream()
                .sorted(Comparator.comparing(GaragePlace::getId))
                .map(GaragePlace::toString)
                .collect(Collectors.toList());

        csvWriter.writeEntities(garagePlaces,
                Constants.GARAGE_PLACES_CSV_HEADER,
                Constants.PATH_TO_GARAGE_PLACES_CSV);
    }

    @Override
    public void importGaragePlacesFromJson() {

        jsonReader
                .readEntities(GaragePlace.class, Constants.PATH_TO_GARAGE_PLACES_JSON)
                .forEach(importGaragePlace -> {
                    GaragePlace garagePlace = null;
                    try {
                        garagePlace = getGaragePlaceById(importGaragePlace.getId());
                    } catch (Exception exception) {
                        logger.error("Can't get garage place", exception);
                    }
                    if (garagePlace == null) {
                        try {
                            garagePlaceDao.addGaragePlace(importGaragePlace);
                        } catch (Exception exception) {
                            logger.error("Can't add garage place", exception);
                        }
                    } else if (!garagePlace.equals(importGaragePlace)) {
                        garagePlace.setNumber(importGaragePlace.getNumber());
                        garagePlace.setSpace(importGaragePlace.getSpace());
                    }
                });
    }

    @Override
    public void exportGaragePlacesToJson() throws Exception {
        jsonWriter.writeEntities(garagePlaceDao.getGaragePlaces(), Constants.PATH_TO_GARAGE_PLACES_JSON);
    }
}