package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.CraftsmanDAO;
import com.senlainc.warsaw.tyurin.dao.ICraftsmanDAO;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.OrderStatus;

import java.util.*;
import java.util.stream.Collectors;

public class CraftsmanService implements ICraftsmanService{

    private static CraftsmanService INSTANCE;
    private ICraftsmanDAO craftsmanDAO;
    private IOrderService orderService;

    private CraftsmanService() {
        craftsmanDAO = CraftsmanDAO.getInstance();
        orderService =OrderService.getInstance();
    }

    public static CraftsmanService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CraftsmanService();
        }
        return INSTANCE;
    }


    @Override
    public void addCraftsman(Craftsman craftsman) {
        craftsmanDAO
                .getCraftsmen()
                .add(craftsman);
    }

    @Override
    public void removeCraftsmen(Craftsman craftsman) {
        craftsmanDAO
                .getCraftsmen()
                .remove(craftsman);
    }

    @Override
    public List<Craftsman> getCraftsmenByOrder(Order order) {
        List<Craftsman> craftsmen = new ArrayList<>();

        order
                .getCraftsmenId()
                .forEach(id -> craftsmen.add(getCraftsmanById(id)));

        return craftsmen;
    }

    @Override
    public List<Craftsman> getSortedAlphabetically() {
        return craftsmanDAO
                .getCraftsmen()
                .stream()
                .sorted(Comparator
                        .comparing(craftsman -> ((Craftsman)craftsman)
                                .getSurname())
                        .thenComparing(craftsman -> ((Craftsman)craftsman)
                                .getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Craftsman> getSortedByBusyness() {
        Map<Long, Integer> craftsmenBusiness = new HashMap<>();
        List<Craftsman> sortedCraftsmen = new ArrayList<>();

        orderService
                .getOrders()
                .stream()
                .filter(order -> ((!order
                        .getOrderStatus()
                        .equals(OrderStatus.CANCELED))))
                .forEach(order -> order
                                .getCraftsmenId()
                                .forEach(id -> {
                            if (craftsmenBusiness.containsKey(id)) {
                                craftsmenBusiness.replace(id, craftsmenBusiness.get(id) + 1);
                            } else {
                                craftsmenBusiness.put(id, 1);
                            }
                        }));

        craftsmenBusiness
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(id -> {
            sortedCraftsmen.add(getCraftsmanById(id.getKey()));
        });

        return sortedCraftsmen;
    }

    @Override
    public Craftsman getCraftsmanById(Long id) {
        List<Craftsman> craftsmen = craftsmanDAO.getCraftsmen();

        for (Craftsman craftsman : craftsmen) {
            if(craftsman.getId() == id) {
                return craftsman;
            }
        }

        return null;
    }

    @Override
    public Craftsman createCraftsmen(String data) {
        Craftsman craftsman = new Craftsman();
        String[] keyValuePairs = data.split(",");
        Arrays.stream(keyValuePairs).forEach(keyValue -> {
            if (keyValue.startsWith("id")) {
                craftsman.setId(Long.parseLong(keyValue.substring(keyValue.indexOf(":") + 1)));
            } else if (keyValue.startsWith("name")) {
                craftsman.setName(keyValue.substring(keyValue.indexOf(":") + 1));
            } else if (keyValue.startsWith("surname")) {
                craftsman.setSurname(keyValue.substring(keyValue.indexOf(":") + 1));
            }
        });
        return craftsman;
    }

    @Override
    public List<Craftsman> getCraftsmen() {
        return craftsmanDAO.getCraftsmen();
    }
}