package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.GaragePlaceDAO;
import com.senlainc.warsaw.tyurin.dao.IGaragePlaceDAO;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.OrderStatus;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvReader;
import com.senlainc.warsaw.tyurin.util.csvHandlers.CsvWriter;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonReader;
import com.senlainc.warsaw.tyurin.util.jsonHandlers.JsonWriter;
import com.senlainc.warsaw.tyurin.util.propertyHandlers.PropertyReader;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GaragePlaceService implements IGaragePlaceService{

    private static GaragePlaceService INSTANCE;
    private IGaragePlaceDAO garagePlaceDAO;
    private IOrderService orderService;
    private ICraftsmanService craftsmanService;
    private PropertyReader propertyReader;
    private CsvReader csvReader;
    private CsvWriter csvWriter;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private GaragePlaceService() {
        garagePlaceDAO = GaragePlaceDAO.getInstance();
        orderService = OrderService.getInstance();
        craftsmanService = CraftsmanService.getInstance();
        propertyReader = PropertyReader.getInstance();
        csvReader = CsvReader.getInstance();
        csvWriter = CsvWriter.getInstance();
        jsonReader = JsonReader.getInstance();
        jsonWriter = JsonWriter.getInstance();
    }

    public static GaragePlaceService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GaragePlaceService();
        }
        return INSTANCE;
    }

    @Override
    public void addGaragePlace(GaragePlace garagePlace) {

        if (Boolean.parseBoolean(propertyReader.getProperty(Constants.ABILITY_TO_ADD_GARAGE_PLACE))) {
            garagePlaceDAO.addGaragePlace(garagePlace);
        } else {
            System.out.println("Adding garage places was prohibited");
        }
    }

    @Override
    public void removeGaragePlace(long id) {

        if (Boolean.parseBoolean(propertyReader.getProperty(Constants.ABILITY_TO_REMOVE_GARAGE_PLACE))) {
            garagePlaceDAO
                    .getGaragePlaces()
                    .remove(garagePlaceDAO
                            .getGaragePlaces()
                            .stream()
                            .filter(garagePlace -> garagePlace.getId() == id)
                            .findFirst()
                            .orElse(null));
        } else {
            System.out.println("Removing garage places was prohibited");
        }
    }

    @Override
    public List<GaragePlace> getAvailablePlaces() {
        List<GaragePlace> availablePlaces = new ArrayList<>();

        List<Long> unavailableGaragePlaceNumbers = orderService
                .getOrders()
                .stream()
                .filter(order -> order
                        .getOrderStatus().equals(OrderStatus.IN_PROGRESS))
                .map(Order::getGaragePlaceId)
                .collect(Collectors.toList());

        garagePlaceDAO.getGaragePlaces().forEach(garagePlace -> {
            if (!unavailableGaragePlaceNumbers.contains(garagePlace.getId())) {
                availablePlaces.add(garagePlace);
            }
        });

        return availablePlaces;
    }

    @Override
    public long getAvailablePlacesAmount(LocalDateTime localDateTime) {
        LocalDateTime searchTime = localDateTime
                .minusMinutes(localDateTime.getMinute())
                .minusSeconds(localDateTime.getSecond())
                .minusNanos(localDateTime.getNano());

        List<Order> searchTimeOrders = orderService
                .getOrders()
                .stream()
                .filter(order -> order
                        .getStartDate()
                        .isBefore(searchTime) && order
                        .getCompletionDate()
                        .isAfter(searchTime) && !order
                .getOrderStatus().equals(OrderStatus.CANCELED))
                .collect(Collectors.toList());

        List<Long> orders = searchTimeOrders
                .stream()
                .map(Order::getGaragePlaceId)
                .collect(Collectors.toList());

        int availablePlacesAmount = garagePlaceDAO.getGaragePlaces().size() - orders.size();

        int unavailableCraftsmenAmount = searchTimeOrders
                .stream()
                .map(Order::getCraftsmenId)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet())
                .size();

        int availableCraftsmenAmount = craftsmanService.getCraftsmen().size() - unavailableCraftsmenAmount;

        return Math.min(availablePlacesAmount, availableCraftsmenAmount);
    }

    @Override
    public LocalDateTime getNearestAvailableDate() {
        LocalDateTime currantTime = LocalDateTime.now();

        LocalDateTime searchTime = currantTime
                .minusMinutes(currantTime.getMinute())
                .minusSeconds(currantTime.getSecond())
                .minusNanos(currantTime.getNano())
                .plusHours(1);

        if (getAvailablePlacesAmount(searchTime) > 0) {
            return searchTime;
        }

        List<Order> ordersInProgress = orderService
                .getOrders()
                .stream()
                .filter(order -> (order
                        .getOrderStatus().equals(OrderStatus.IN_PROGRESS)) || (order
                        .getOrderStatus().equals(OrderStatus.NEW)))
                .sorted(Comparator.comparing(Order::getCompletionDate))
                .collect(Collectors.toList());

        for (Order order : ordersInProgress) {
            if (getAvailablePlacesAmount(order.getCompletionDate()) > 0) {
                return order.getCompletionDate();
            }
        }

        return null;
    }

    @Override
    public GaragePlace createGaragePlace(long id, int number, double space) {
        GaragePlace garagePlace = new GaragePlace();
        garagePlace.setId(id);
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
                    GaragePlace garagePlace = getGaragePlaceById(importGaragePlace.getId());
                    if (garagePlace == null) {
                        garagePlaceDAO.addGaragePlace(importGaragePlace);
                    } else if (!garagePlace.equals(importGaragePlace)) {
                        garagePlace.setNumber(importGaragePlace.getNumber());
                        garagePlace.setSpace(importGaragePlace.getSpace());
                    }
                });
    }

    @Override
    public GaragePlace getGaragePlaceById(Long id) {

        return garagePlaceDAO
                .getGaragePlaces()
                .stream()
                .filter(garagePlace -> garagePlace.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void exportGaragePlacesToCsv() {

        List<String> garagePlaces = garagePlaceDAO
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
                    GaragePlace garagePlace = getGaragePlaceById(importGaragePlace.getId());
                    if (garagePlace == null) {
                        garagePlaceDAO.addGaragePlace(importGaragePlace);
                    } else if (!garagePlace.equals(importGaragePlace)) {
                        garagePlace.setNumber(importGaragePlace.getNumber());
                        garagePlace.setSpace(importGaragePlace.getSpace());
                    }
                });
    }

    @Override
    public void exportGaragePlacesToJson() {
        jsonWriter.writeEntities(garagePlaceDAO.getGaragePlaces(), Constants.PATH_TO_GARAGE_PLACES_JSON);
    }
}