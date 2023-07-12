package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.dao.CraftsmanDAO;
import com.senlainc.warsaw.tyurin.dao.GaragePlaceDAO;
import com.senlainc.warsaw.tyurin.dao.OrderDAO;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.entity.OrderStatus;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import com.senlainc.warsaw.tyurin.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        GaragePlaceService garagePlaceService = GaragePlaceService.getInstance();
        CraftsmanService craftsmanService = CraftsmanService.getInstance();
        OrderService orderService = OrderService.getInstance();

        Craftsman bob = new Craftsman();
        bob.setName("bob");
        bob.setSurname("smith");
        bob.setId(1);
        bob.getSchedule().put(LocalDateTime.of(2023, 7, 01, 12, 0), true);
        bob.getSchedule().put(LocalDateTime.of(2023, 7, 05, 12, 0), true);
        bob.getSchedule().put(LocalDateTime.of(2023, 7, 10, 12, 0), true);
        bob.getSchedule().put(LocalDateTime.of(2023, 7, 15, 12, 0), true);
        bob.getSchedule().put(LocalDateTime.of(2023, 7, 20, 12, 0), true);
        bob.getSchedule().put(LocalDateTime.of(2023, 7, 25, 12, 0), true);
        bob.getSchedule().put(LocalDateTime.of(2023, 7, 30, 12, 0), true);

        Craftsman ted = new Craftsman();
        ted.setName("ted");
        ted.setSurname("bons");
        ted.setId(2);
        ted.getSchedule().put(LocalDateTime.of(2023, 7, 01, 12, 0), true);
        ted.getSchedule().put(LocalDateTime.of(2023, 7, 05, 12, 0), true);
        ted.getSchedule().put(LocalDateTime.of(2023, 7, 10, 12, 0), true);
        ted.getSchedule().put(LocalDateTime.of(2023, 7, 15, 12, 0), true);
        ted.getSchedule().put(LocalDateTime.of(2023, 7, 20, 12, 0), true);
        ted.getSchedule().put(LocalDateTime.of(2023, 7, 25, 12, 0), true);
        ted.getSchedule().put(LocalDateTime.of(2023, 7, 30, 12, 0), true);

        Craftsman stan = new Craftsman();
        stan.setName("stan");
        stan.setSurname("smith");
        stan.setId(3);
        stan.getSchedule().put(LocalDateTime.of(2023, 7, 01, 12, 0), true);
        stan.getSchedule().put(LocalDateTime.of(2023, 7, 05, 12, 0), true);
        stan.getSchedule().put(LocalDateTime.of(2023, 7, 10, 12, 0), true);
        stan.getSchedule().put(LocalDateTime.of(2023, 7, 11, 13, 0), true);
        stan.getSchedule().put(LocalDateTime.of(2023, 7, 15, 12, 0), true);
        stan.getSchedule().put(LocalDateTime.of(2023, 7, 20, 12, 0), true);
        stan.getSchedule().put(LocalDateTime.of(2023, 7, 25, 12, 0), true);
        stan.getSchedule().put(LocalDateTime.of(2023, 7, 30, 12, 0), true);

        GaragePlace garagePlace1 = new GaragePlace();
        garagePlace1.setId(1);
        garagePlace1.setNumber(1);
        garagePlace1.setSpace(10.0);
        garagePlace1.getSchedule().put(LocalDateTime.of(2023, 7, 01, 12, 0), true);
        garagePlace1.getSchedule().put(LocalDateTime.of(2023, 7, 05, 12, 0), true);
        garagePlace1.getSchedule().put(LocalDateTime.of(2023, 7, 10, 12, 0), true);
        garagePlace1.getSchedule().put(LocalDateTime.of(2023, 7, 15, 12, 0), true);
        garagePlace1.getSchedule().put(LocalDateTime.of(2023, 7, 20, 12, 0), true);
        garagePlace1.getSchedule().put(LocalDateTime.of(2023, 7, 25, 12, 0), true);
        garagePlace1.getSchedule().put(LocalDateTime.of(2023, 7, 30, 12, 0), true);

        GaragePlace garagePlace2 = new GaragePlace();
        garagePlace2.setId(2);
        garagePlace2.setNumber(2);
        garagePlace2.setSpace(10.0);
        garagePlace2.getSchedule().put(LocalDateTime.of(2023, 7, 01, 12, 0), true);
        garagePlace2.getSchedule().put(LocalDateTime.of(2023, 7, 05, 12, 0), true);
        garagePlace2.getSchedule().put(LocalDateTime.of(2023, 7, 10, 12, 0), true);
        garagePlace2.getSchedule().put(LocalDateTime.of(2023, 7, 11, 13, 0), true);
        garagePlace2.getSchedule().put(LocalDateTime.of(2023, 7, 15, 12, 0), true);
        garagePlace2.getSchedule().put(LocalDateTime.of(2023, 7, 20, 12, 0), true);
        garagePlace2.getSchedule().put(LocalDateTime.of(2023, 7, 25, 12, 0), true);
        garagePlace2.getSchedule().put(LocalDateTime.of(2023, 7, 30, 12, 0), true);

        GaragePlace garagePlace3 = new GaragePlace();
        garagePlace3.setId(3);
        garagePlace3.setNumber(3);
        garagePlace3.setSpace(10.0);
        garagePlace3.getSchedule().put(LocalDateTime.of(2023, 7, 01, 12, 0), true);
        garagePlace3.getSchedule().put(LocalDateTime.of(2023, 7, 05, 12, 0), true);
        garagePlace3.getSchedule().put(LocalDateTime.of(2023, 7, 10, 12, 0), true);
        garagePlace3.getSchedule().put(LocalDateTime.of(2023, 7, 15, 12, 0), true);
        garagePlace3.getSchedule().put(LocalDateTime.of(2023, 7, 20, 12, 0), true);
        garagePlace3.getSchedule().put(LocalDateTime.of(2023, 7, 25, 12, 0), true);
        garagePlace3.getSchedule().put(LocalDateTime.of(2023, 7, 30, 12, 0), true);

        Order order1 = new Order();
        order1.setId(1);
        order1.setStartDate(LocalDateTime.of(2023, 7, 05, 12, 0));
        order1.setCompletionDate(LocalDateTime.of(2023, 7, 05, 13, 0));
        order1.setOrderStatus(OrderStatus.COMPLETED);
        order1.addCraftsman(ted);
        order1.addCraftsman(bob);

        Order order2 = new Order();
        order2.setId(2);
        order2.setStartDate(LocalDateTime.of(2023, 7, 30, 12, 0));
        order2.setCompletionDate(LocalDateTime.of(2023, 7, 30, 13, 0));
        order2.addCraftsman(ted);
        order2.addCraftsman(bob);
        order2.setOrderStatus(OrderStatus.CANCELED);

        Order order3 = new Order();
        order3.setId(3);
        order3.setStartDate(LocalDateTime.of(2023, 7, 15, 12, 0));
        order3.setCompletionDate(LocalDateTime.of(2024, 7, 15, 13, 0));
        order3.addCraftsman(ted);
        order3.addCraftsman(bob);

        GaragePlaceDAO garagePlaceDAO = GaragePlaceDAO.getInstance();
        garagePlaceDAO.addGaragePlace(garagePlace1);
        garagePlaceDAO.addGaragePlace(garagePlace2);
        garagePlaceDAO.addGaragePlace(garagePlace3);

        CraftsmanDAO craftsmanDAO = CraftsmanDAO.getInstance();
        craftsmanDAO.addCraftsman(bob);
        craftsmanDAO.addCraftsman(ted);
        craftsmanDAO.addCraftsman(stan);

        OrderDAO orderDAO = OrderDAO.getInstance();
        orderDAO.addOrder(order1);
        orderDAO.addOrder(order2);
        orderDAO.addOrder(order3);

        //get list of available places at the time of request
        //example - if current time is 11:15 then request return list of places that are available in 11:00 (from 11:00 till 12:00)
        List<GaragePlace> currentlyAvailableGaragePlaces = garagePlaceService.getAvailablePlaces();
        currentlyAvailableGaragePlaces.forEach(place -> System.out.println("currently available garage place id = " + place.getId()));

        //get nearest available date of garage place
        //minimum 1 craftsman also should available at the same time
        System.out.println("nearest available date for booking = " + garagePlaceService.getNearestAvailableDate());

        //get available places amount
        //minimum 1 craftsman also should available at the same time (minimum 1 craftsman per 1 garage place)
        System.out.println("amount of available garage places for a date = " + garagePlaceService
                .getAvailablePlacesAmount(LocalDateTime.of(2023, 7, 15, 12, 0)));

        //get list of all orders sorted by start date
        orderService.getSortedByStartDate().forEach(order ->
                System.out.println("order id sorted by start date = " + order.getId()));

        //get list of all orders sorted by completion date
        orderService.getSortedByCompletionDate().forEach(order ->
                System.out.println("order id sorted by completion date = " + order.getId()));

        //get list of all orders sorted by submission date
        orderService.getSortedBySubmissionDate().forEach(order ->
                System.out.println("order id sorted by submission date = " + order.getId()));

        //get list of all orders sorted by price
        orderService.getSortedByPrice().forEach(order ->
                System.out.println("order id sorted by price = " + order.getId()));

        //get list of all completed/deleted/canceled orders sorted by submission date
        orderService.getArchivedOrdersSortedBySubmissionDate().forEach(order ->
                System.out.println("archived order id sorted by submission date = " + order.getId()));

        //get list of all completed/deleted/canceled orders sorted by completion date
        orderService.getArchivedOrdersSortedByCompletionDate().forEach(order ->
                System.out.println("archived order id sorted by completion date = " + order.getId()));

        //get list of all completed/deleted/canceled orders sorted by price
        orderService.getArchivedOrdersSortedByPrice().forEach(order ->
                System.out.println("archived order id sorted by price = " + order.getId()));

        //get list of all currently executed orders sorted by submission date
        orderService.getCurrentlyExecutedOrdersSortedBySubmissionDate().forEach(order ->
                System.out.println("currently executed order id sorted by submission date = " + order.getId()));

        //get list of all currently executed orders sorted by completion date
        orderService.getCurrentlyExecutedOrdersSortedByCompletionDate().forEach(order ->
                System.out.println("currently executed order id sorted by completion date = " + order.getId()));

        //get list of all currently executed orders sorted by price
        orderService.getCurrentlyExecutedOrdersSortedByPrice().forEach(order ->
                System.out.println("currently executed order id sorted by price = " + order.getId()));

        //get order executed by specific craftsman
        Order order = orderService.getOrderByCraftsmen(ted);
        if (order != null) {
            System.out.println("order id executed by specific craftsman = " + order.getId());
        }

        //get list of all craftsmen sorted by busyness
        craftsmanService.getSortedByBusyness().forEach(craftsman ->
                System.out.println("craftsman id sorted by busyness = " + craftsman.getId()));

        //get list of all craftsmen sorted alphabetically
        craftsmanService.getSortedAlphabetically().forEach(craftsman ->
                System.out.println("craftsman id sorted alphabetically = " + craftsman.getId()));

        //get list of all craftsmen executed specific order
        craftsmanService.getCraftsmenByOrder(order1).forEach(craftsman ->
                System.out.println("craftsman id executed specific order = " + craftsman.getId()));
    }
}
