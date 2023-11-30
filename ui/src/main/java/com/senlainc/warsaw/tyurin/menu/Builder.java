package com.senlainc.warsaw.tyurin.menu;

import com.senlainc.warsaw.tyurin.action.craftsman.AddCraftsman;
import com.senlainc.warsaw.tyurin.action.craftsman.ExportCraftsmenToCsv;
import com.senlainc.warsaw.tyurin.action.craftsman.ExportCraftsmenToJson;
import com.senlainc.warsaw.tyurin.action.craftsman.GetCraftsmenByOrder;
import com.senlainc.warsaw.tyurin.action.craftsman.GetSortedAlphabetically;
import com.senlainc.warsaw.tyurin.action.craftsman.GetSortedByBusyness;
import com.senlainc.warsaw.tyurin.action.craftsman.ImportCraftsmenFromCsv;
import com.senlainc.warsaw.tyurin.action.craftsman.ImportCraftsmenFromJson;
import com.senlainc.warsaw.tyurin.action.craftsman.RemoveCraftsman;
import com.senlainc.warsaw.tyurin.action.garageplace.AddGaragePlace;
import com.senlainc.warsaw.tyurin.action.garageplace.ExportGaragePlacesToCsv;
import com.senlainc.warsaw.tyurin.action.garageplace.ExportGaragePlacesToJson;
import com.senlainc.warsaw.tyurin.action.garageplace.GetAvailablePlaces;
import com.senlainc.warsaw.tyurin.action.garageplace.GetAvailablePlacesAmount;
import com.senlainc.warsaw.tyurin.action.garageplace.GetNearestAvailableDate;
import com.senlainc.warsaw.tyurin.action.garageplace.ImportGaragePlacesFromCsv;
import com.senlainc.warsaw.tyurin.action.garageplace.ImportGaragePlacesFromJson;
import com.senlainc.warsaw.tyurin.action.garageplace.RemoveGaragePlace;
import com.senlainc.warsaw.tyurin.action.order.*;
import com.senlainc.warsaw.tyurin.action.util.ExitProgram;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import com.senlainc.warsaw.tyurin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Builder {

    private Menu rootMenu;
    @Autowired
    private CraftsmanService craftsmanService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private GaragePlaceService garagePlaceService;

    public Builder() {}

    public void buildMenu() {
        rootMenu = new Menu();

        rootMenu.setName("Root menu");

        rootMenu.getMenuItems().add(new MenuItem("Craftsman menu", getCraftsmanMenu()));
        rootMenu.getMenuItems().add(new MenuItem("Order menu", getOrderMenu()));
        rootMenu.getMenuItems().add(new MenuItem("Garage place menu", getGaragePlaceMenu()));
        rootMenu.getMenuItems().add(new MenuItem("Exit program", new ExitProgram(), rootMenu));
    }

    public Menu getRootMenu() {
       return rootMenu;
    }

    private Menu getCraftsmanMenu() {

        Menu craftsmanMenu = new Menu();

        craftsmanMenu.setName("Craftsman menu");

        craftsmanMenu.getMenuItems().add(new MenuItem("Add craftsman", new AddCraftsman(craftsmanService), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Remove craftsman", new RemoveCraftsman(craftsmanService), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Get sorted craftsmen alphabetically", new GetSortedAlphabetically(craftsmanService), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Get sorted craftsmen by busyness", new GetSortedByBusyness(craftsmanService), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Get craftsmen by order", new GetCraftsmenByOrder(craftsmanService), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Import craftsmen from csv", new ImportCraftsmenFromCsv(craftsmanService), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Export craftsmen to csv", new ExportCraftsmenToCsv(craftsmanService), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Import craftsmen from json", new ImportCraftsmenFromJson(craftsmanService), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Export craftsmen to json", new ExportCraftsmenToJson(craftsmanService), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Exit to root menu", getRootMenu()));

        return craftsmanMenu;
    }

    private Menu getOrderMenu() {

        Menu orderMenu = new Menu();

        orderMenu.setName("Order menu");

        orderMenu.getMenuItems().add(new MenuItem("Add order", new AddOrder(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Remove order", new RemoveOrder(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get order by id", new GetOrderById(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get archived orders sorted by completion date", new GetArchivedOrdersSortedByCompletionDate(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get archived orders sorted by price", new GetArchivedOrdersSortedByPrice(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get archived orders sorted by submission date", new GetArchivedOrdersSortedBySubmissionDate(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get currently executed orders sorted by completion date", new GetCurrentlyExecutedOrdersSortedByCompletionDate(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get currently executed orders sorted by price", new GetCurrentlyExecutedOrdersSortedByPrice(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get currently executed orders sorted by submission date", new GetCurrentlyExecutedOrdersSortedBySubmissionDate(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get order by craftsman", new GetOrderByCraftsman(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get sorted orders by completion date", new GetSortedByCompletionDate(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get sorted orders by price", new GetSortedByPrice(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get sorted orders by start date", new GetSortedByStartDate(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get sorted orders by submission date", new GetSortedBySubmissionDate(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Shift order completion date time", new ShiftCompletionDateTime(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Shift order start date time", new ShiftStartDateTime(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Change order status", new ChangeOrderStatus(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Import orders from csv", new ImportOrdersFromCsv(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Export orders to csv", new ExportOrdersToCsv(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Import orders from json", new ImportOrdersFromJson(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Export orders to json", new ExportOrdersToJson(orderService), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Exit to root menu", getRootMenu()));

        return orderMenu;
    }

    private Menu getGaragePlaceMenu() {

        Menu garagePlaceMenu = new Menu();

        garagePlaceMenu.setName("Garage menu");

        garagePlaceMenu.getMenuItems().add(new MenuItem("Add garage place", new AddGaragePlace(garagePlaceService), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Remove garage place", new RemoveGaragePlace(garagePlaceService), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Get available garage places amount", new GetAvailablePlacesAmount(garagePlaceService), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Get available garage places", new GetAvailablePlaces(garagePlaceService), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Get nearest available date", new GetNearestAvailableDate(garagePlaceService), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Import garage places from csv", new ImportGaragePlacesFromCsv(garagePlaceService), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Export garage places to csv", new ExportGaragePlacesToCsv(garagePlaceService), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Import garage places from json", new ImportGaragePlacesFromJson(garagePlaceService), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Export garage places to json", new ExportGaragePlacesToJson(garagePlaceService), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Exit to root menu", getRootMenu()));

        return garagePlaceMenu;
    }
}
