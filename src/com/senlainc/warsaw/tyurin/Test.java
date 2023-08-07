package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.entity.Order;
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

        Craftsman bob = craftsmanService.createCraftsmen("id:1,name:bob,surname:smith");
        Craftsman ted = craftsmanService.createCraftsmen("id:2,name:ted,surname:bons");
        Craftsman stan = craftsmanService.createCraftsmen("id:3,name:stan,surname:smith");

        GaragePlace garagePlace1 = garagePlaceService.createGaragePlace("id:1,number:1,space:10.0");
        GaragePlace garagePlace2 = garagePlaceService.createGaragePlace("id:2,number:2,space:10.0");
        GaragePlace garagePlace3 = garagePlaceService.createGaragePlace("id:3,number:3,space:10.0");

        Order order1 = orderService.createOrder("id:1,price:5,submissionDate:2023-07-26T21:45," +
                "startDate:2023-07-05T12:00,completionDate:2023-07-12T20:00," +
                "orderStatus:COMPLETED,craftsmenId:2;1,garagePlaceId:1");

        Order order2 = orderService.createOrder("id:2,price:25,submissionDate:2023-07-26T21:35," +
                "startDate:2023-07-12T12:00,completionDate:2023-07-28T13:00," +
                "orderStatus:IN_PROGRESS,craftsmenId:2;1,garagePlaceId:2");

        Order order3 = orderService.createOrder("id:3,price:15,submissionDate:2023-07-26T20:35," +
                "startDate:2023-07-24T12:00,completionDate:2023-07-27T13:00," +
                "orderStatus:IN_PROGRESS,craftsmenId:2;1,garagePlaceId:3");

        Order order4 = orderService.createOrder("id:4,price:60,submissionDate:2023-07-26T10:35," +
                "startDate:2023-08-14T12:00,completionDate:2023-08-29T13:00," +
                "orderStatus:NEW,craftsmenId:2,garagePlaceId:2");

        Order order5 = orderService.createOrder("id:4,price:60,submissionDate:2023-07-26T10:35," +
                "startDate:2023-09-14T12:00,completionDate:2023-09-29T13:00," +
                "orderStatus:NEW,craftsmenId:3,garagePlaceId:2");

        garagePlaceService.addGaragePlace(garagePlace1);
        garagePlaceService.addGaragePlace(garagePlace2);
        garagePlaceService.addGaragePlace(garagePlace3);

        craftsmanService.addCraftsman(bob);
        craftsmanService.addCraftsman(ted);
        craftsmanService.addCraftsman(stan);

        orderService.addOrder(order1);
        orderService.addOrder(order2);
        orderService.addOrder(order3);
        orderService.addOrder(order4);
        orderService.addOrder(order5);

        //get list of available places at the time of request
        //example - if current time is 11:15 then request return list of places that are available in 11:00 (from 11:00 till 12:00)
        List<GaragePlace> currentlyAvailableGaragePlaces = garagePlaceService.getAvailablePlaces();
        currentlyAvailableGaragePlaces.forEach(place -> System.out.println("currently available garage place id = " + place.getId()));

        //get nearest available date of garage place
        //minimum 1 craftsman also should available at the same time
        System.out.println("nearest available date for booking = " + garagePlaceService.getNearestAvailableDate());

        //get available places amount
        //minimum 1 craftsman also should available at the same time (minimum 1 craftsman per 1 garage place)
        System.out.println("===============================================");
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
        Order order = orderService.getOrderByCraftsmen(ted.getId());
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
        craftsmanService.getCraftsmenByOrder(order1.getId()).forEach(craftsman ->
                System.out.println("craftsman id executed specific order = " + craftsman.getId()));
    }
}
