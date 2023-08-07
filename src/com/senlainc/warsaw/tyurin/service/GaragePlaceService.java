package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.GaragePlaceDAO;
import com.senlainc.warsaw.tyurin.dao.IGaragePlaceDAO;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.entity.Order;
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

        List<GaragePlace> garagePlaces = garagePlaceDAO.getGaragePlaces();

        for (GaragePlace garagePlace : garagePlaces) {
            if(garagePlace.getId() == id) {
                garagePlaceDAO
                        .getGaragePlaces()
                        .remove(garagePlace);
                break;
            }
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
    public GaragePlace createGaragePlace(String data) {
        GaragePlace garagePlace = new GaragePlace();
        String[] keyValuePairs = data.split(",");
        Arrays.stream(keyValuePairs).forEach(keyValue -> {
            if (keyValue.startsWith("id")) {
                garagePlace.setId(Long.parseLong(keyValue.substring(keyValue.indexOf(":") + 1)));
            } else if (keyValue.startsWith("number")) {
                garagePlace.setNumber(Integer.parseInt(keyValue.substring(keyValue.indexOf(":") + 1)));
            } else if (keyValue.startsWith("space")) {
                garagePlace.setSpace(Double.parseDouble(keyValue.substring(keyValue.indexOf(":") + 1)));
            }
        });
        return garagePlace;
    }
}