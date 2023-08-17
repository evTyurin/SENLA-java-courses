package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.GaragePlaceDAO;
import com.senlainc.warsaw.tyurin.dao.IGaragePlaceDAO;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.Constants;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GaragePlaceService implements IGaragePlaceService{

    private static GaragePlaceService INSTANCE;
    private IGaragePlaceDAO garagePlaceDAO;
    private IOrderService orderService;
    private ICraftsmanService craftsmanService;

    private GaragePlaceService() {
        garagePlaceDAO = GaragePlaceDAO.getInstance();
        orderService = OrderService.getInstance();
        craftsmanService = CraftsmanService.getInstance();
    }

    public static GaragePlaceService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GaragePlaceService();
        }
        return INSTANCE;
    }

    @Override
    public void addGaragePlace(GaragePlace garagePlace) {
        garagePlaceDAO.addGaragePlace(garagePlace);
    }

    @Override
    public void removeGaragePlace(long id) {

        garagePlaceDAO
                .getGaragePlaces()
                .remove(garagePlaceDAO
                        .getGaragePlaces()
                        .stream()
                        .filter(garagePlace -> garagePlace.getId() == id)
                        .findFirst()
                        .orElse(null));
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
    public GaragePlace createGaragePlace(String data) {
        GaragePlace garagePlace = new GaragePlace();
        String[] keyValuePairs = data.split(",");
        Arrays.stream(keyValuePairs).forEach(keyValue -> {
            if (keyValue.startsWith("id")) {
                try {
                    garagePlace.setId(Long.parseLong(keyValue.substring(keyValue.indexOf(":") + 1)));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("You enter not a number as garage place id");
                }
            } else if (keyValue.startsWith("number")) {
                try {
                    garagePlace.setNumber(Integer.parseInt(keyValue.substring(keyValue.indexOf(":") + 1)));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("You enter not a number as garage place number");
                }
            } else if (keyValue.startsWith("space")) {
                try {
                    garagePlace.setSpace(Double.parseDouble(keyValue.substring(keyValue.indexOf(":") + 1)));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("You enter not a number as garage place space");
                }
            }
        });
        return garagePlace;
    }

    @Override
    public void importGaragePlaces() {

        garagePlaceDAO
                .importGaragePlaces(Constants.PATH_TO_GARAGE_PLACES_CSV)
                .stream()
                .map(this::createGaragePlace)
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
    public void exportGaragePlaces() {

        List<GaragePlace> garagePlaces = garagePlaceDAO
                .getGaragePlaces()
                .stream()
                .sorted(Comparator.comparing(GaragePlace::getId))
                .collect(Collectors.toList());

        garagePlaceDAO.exportGaragePlaces(garagePlaces, Constants.PATH_TO_GARAGE_PLACES_CSV);
    }
}