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
import com.senlainc.warsaw.tyurin.action.garagePlace.AddGaragePlace;
import com.senlainc.warsaw.tyurin.action.garagePlace.ExportGaragePlacesToCsv;
import com.senlainc.warsaw.tyurin.action.garagePlace.ExportGaragePlacesToJson;
import com.senlainc.warsaw.tyurin.action.garagePlace.GetAvailablePlaces;
import com.senlainc.warsaw.tyurin.action.garagePlace.GetAvailablePlacesAmount;
import com.senlainc.warsaw.tyurin.action.garagePlace.GetNearestAvailableDate;
import com.senlainc.warsaw.tyurin.action.garagePlace.ImportGaragePlacesFromCsv;
import com.senlainc.warsaw.tyurin.action.garagePlace.ImportGaragePlacesFromJson;
import com.senlainc.warsaw.tyurin.action.garagePlace.RemoveGaragePlace;
import com.senlainc.warsaw.tyurin.action.order.AddOrder;
import com.senlainc.warsaw.tyurin.action.order.ChangeOrderStatus;
import com.senlainc.warsaw.tyurin.action.order.ExportOrdersToCsv;
import com.senlainc.warsaw.tyurin.action.order.ExportOrdersToJson;
import com.senlainc.warsaw.tyurin.action.order.GetArchivedOrdersSortedByCompletionDate;
import com.senlainc.warsaw.tyurin.action.order.GetArchivedOrdersSortedByPrice;
import com.senlainc.warsaw.tyurin.action.order.GetArchivedOrdersSortedBySubmissionDate;
import com.senlainc.warsaw.tyurin.action.order.GetCurrentlyExecutedOrdersSortedByCompletionDate;
import com.senlainc.warsaw.tyurin.action.order.GetCurrentlyExecutedOrdersSortedByPrice;
import com.senlainc.warsaw.tyurin.action.order.GetCurrentlyExecutedOrdersSortedBySubmissionDate;
import com.senlainc.warsaw.tyurin.action.order.GetOrderByCraftsman;
import com.senlainc.warsaw.tyurin.action.order.GetSortedByCompletionDate;
import com.senlainc.warsaw.tyurin.action.order.GetSortedByPrice;
import com.senlainc.warsaw.tyurin.action.order.GetSortedByStartDate;
import com.senlainc.warsaw.tyurin.action.order.GetSortedBySubmissionDate;
import com.senlainc.warsaw.tyurin.action.order.ImportOrdersFromCsv;
import com.senlainc.warsaw.tyurin.action.order.ImportOrdersFromJson;
import com.senlainc.warsaw.tyurin.action.order.RemoveOrder;
import com.senlainc.warsaw.tyurin.action.order.ShiftCompletionDateTime;
import com.senlainc.warsaw.tyurin.action.order.ShiftStartDateTime;
import com.senlainc.warsaw.tyurin.action.util.ExitProgram;

public class Builder {

    private static Builder INSTANCE;

    private Menu rootMenu;

    private AddCraftsman addCraftsman;

    public static Builder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Builder();
        }
        return INSTANCE;
    }

    public Builder() {
        buildMenu();
    }

    public void buildMenu() {
        rootMenu = new Menu();

        rootMenu.setName("Root com.senlainc.warsaw.tyurin.menu");

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

        craftsmanMenu.setName("Craftsman com.senlainc.warsaw.tyurin.menu");

        craftsmanMenu.getMenuItems().add(new MenuItem("Add craftsman", new AddCraftsman(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Remove craftsman", new RemoveCraftsman(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Get sorted craftsmen alphabetically", new GetSortedAlphabetically(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Get sorted craftsmen by busyness", new GetSortedByBusyness(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Get craftsmen by order", new GetCraftsmenByOrder(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Import craftsmen from csv", new ImportCraftsmenFromCsv(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Export craftsmen to csv", new ExportCraftsmenToCsv(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Import craftsmen from json", new ImportCraftsmenFromJson(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Export craftsmen to json", new ExportCraftsmenToJson(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Exit to root com.senlainc.warsaw.tyurin.menu", getRootMenu()));

        return craftsmanMenu;
    }

    private Menu getOrderMenu() {

        Menu orderMenu = new Menu();

        orderMenu.setName("Order com.senlainc.warsaw.tyurin.menu");

        orderMenu.getMenuItems().add(new MenuItem("Add order", new AddOrder(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Remove order", new RemoveOrder(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get archived orders sorted by completion date", new GetArchivedOrdersSortedByCompletionDate(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get archived orders sorted by price", new GetArchivedOrdersSortedByPrice(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get archived orders sorted by submission date", new GetArchivedOrdersSortedBySubmissionDate(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get currently executed orders sorted by completion date", new GetCurrentlyExecutedOrdersSortedByCompletionDate(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get currently executed orders sorted by price", new GetCurrentlyExecutedOrdersSortedByPrice(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get currently executed orders sorted by submission date", new GetCurrentlyExecutedOrdersSortedBySubmissionDate(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get order by craftsman", new GetOrderByCraftsman(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get sorted orders by completion date", new GetSortedByCompletionDate(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get sorted orders by price", new GetSortedByPrice(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get sorted orders by start date", new GetSortedByStartDate(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Get sorted orders by submission date", new GetSortedBySubmissionDate(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Shift order completion date time", new ShiftCompletionDateTime(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Shift order start date time", new ShiftStartDateTime(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Change order status", new ChangeOrderStatus(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Import orders from csv", new ImportOrdersFromCsv(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Export orders to csv", new ExportOrdersToCsv(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Import orders from json", new ImportOrdersFromJson(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Export orders to json", new ExportOrdersToJson(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Exit to root com.senlainc.warsaw.tyurin.menu", getRootMenu()));

        return orderMenu;
    }

    private Menu getGaragePlaceMenu() {

        Menu garagePlaceMenu = new Menu();

        garagePlaceMenu.setName("Garage place com.senlainc.warsaw.tyurin.menu");

        garagePlaceMenu.getMenuItems().add(new MenuItem("Add garage place", new AddGaragePlace(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Remove garage place", new RemoveGaragePlace(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Get available garage places amount", new GetAvailablePlacesAmount(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Get available garage places", new GetAvailablePlaces(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Get nearest available date", new GetNearestAvailableDate(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Import garage places from csv", new ImportGaragePlacesFromCsv(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Export garage places to csv", new ExportGaragePlacesToCsv(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Import garage places from json", new ImportGaragePlacesFromJson(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Export garage places to json", new ExportGaragePlacesToJson(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Exit to root com.senlainc.warsaw.tyurin.menu", getRootMenu()));

        return garagePlaceMenu;
    }
}
