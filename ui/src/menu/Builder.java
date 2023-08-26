package menu;

import action.craftsman.*;
import action.garagePlace.*;
import action.order.*;
import action.util.ExitProgram;

public class Builder {
    private static Builder INSTANCE;
    private Menu rootMenu;

    public static Builder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Builder();
        }
        return INSTANCE;
    }

    private Builder() {
        buildMenu();
    }

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

        craftsmanMenu.getMenuItems().add(new MenuItem("Add craftsman", new AddCraftsman(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Remove craftsman", new RemoveCraftsman(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Get sorted craftsmen alphabetically", new GetSortedAlphabetically(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Get sorted craftsmen by busyness", new GetSortedByBusyness(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Get craftsmen by order", new GetCraftsmenByOrder(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Import craftsmen", new ImportCraftsmen(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Export craftsmen", new ExportCraftsmen(), craftsmanMenu));
        craftsmanMenu.getMenuItems().add(new MenuItem("Exit to root menu", getRootMenu()));

        return craftsmanMenu;
    }

    private Menu getOrderMenu() {

        Menu orderMenu = new Menu();

        orderMenu.setName("Order menu");

        orderMenu.getMenuItems().add(new MenuItem("Add order", new AddOrder(), orderMenu));
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
        orderMenu.getMenuItems().add(new MenuItem("Import orders", new ImportOrders(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Export orders", new ExportOrders(), orderMenu));
        orderMenu.getMenuItems().add(new MenuItem("Exit to root menu", getRootMenu()));

        return orderMenu;
    }

    private Menu getGaragePlaceMenu() {

        Menu garagePlaceMenu = new Menu();

        garagePlaceMenu.setName("Garage place menu");

        garagePlaceMenu.getMenuItems().add(new MenuItem("Add garage place", new AddGaragePlace(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Remove garage place", new RemoveGaragePlace(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Get available garage places amount", new GetAvailablePlacesAmount(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Get available garage places", new GetAvailablePlaces(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Get nearest available date", new GetNearestAvailableDate(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Import garage places", new ImportGaragePlaces(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Export garage places", new ExportGaragePlaces(), garagePlaceMenu));
        garagePlaceMenu.getMenuItems().add(new MenuItem("Exit to root menu", getRootMenu()));

        return garagePlaceMenu;
    }

}
