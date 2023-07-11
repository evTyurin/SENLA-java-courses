package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.CraftsmanDAO;
import com.senlainc.warsaw.tyurin.dao.GaragePlaceDAO;
import com.senlainc.warsaw.tyurin.dao.ICraftsmanDAO;
import com.senlainc.warsaw.tyurin.dao.IGaragePlaceDAO;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class GaragePlaceService implements IGaragePlaceService{

    private static GaragePlaceService INSTANCE;
    private IGaragePlaceDAO garagePlaceDAO;
    private ICraftsmanDAO craftsmanDAO;

    private GaragePlaceService() {
        garagePlaceDAO = GaragePlaceDAO.getInstance();
        craftsmanDAO = CraftsmanDAO.getInstance();
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
    public void deleteGaragePlace(GaragePlace garagePlace) {
        garagePlaceDAO.deleteGaragePlace(garagePlace);
    }

    @Override
    public List<GaragePlace> getAvailablePlaces() {
        LocalDateTime currantTime = LocalDateTime.now();
        LocalDateTime searchTime = currantTime
                .minusMinutes(currantTime.getMinute())
                .minusSeconds(currantTime.getSecond())
                .minusNanos(currantTime.getNano());

        return garagePlaceDAO
                .getGaragePlaces()
                .stream()
                .filter(garagePlace -> Objects.nonNull(garagePlace
                        .getSchedule()
                        .get(searchTime)))
                .filter(garagePlace -> garagePlace
                        .getSchedule()
                        .get(searchTime))
                .collect(Collectors.toList());
    }

    @Override
    public long getAvailablePlacesAmount(LocalDateTime localDateTime) {
        LocalDateTime searchTime = localDateTime
                .minusMinutes(localDateTime.getMinute())
                .minusSeconds(localDateTime.getSecond())
                .minusNanos(localDateTime.getNano());

        long availableGaragePlacesAmount = garagePlaceDAO
                .getGaragePlaces()
                .stream()
                .filter(garagePlace -> Objects.nonNull(garagePlace
                        .getSchedule()
                        .get(searchTime)))
                .filter(garagePlace -> garagePlace
                        .getSchedule()
                        .get(searchTime))
                .count();

        long availableCraftsmenAmount = craftsmanDAO
                .getCraftsmen()
                .stream()
                .filter(craftsman -> Objects.nonNull(craftsman
                        .getSchedule()
                        .get(searchTime)))
                .filter(craftsman -> craftsman
                        .getSchedule()
                        .get(searchTime))
                .count();

        return Math.min(availableGaragePlacesAmount, availableCraftsmenAmount);
    }

    @Override
    public LocalDateTime getNearestAvailableDate() {
        LocalDateTime currantTime = LocalDateTime.now();

        LocalDateTime searchTime = currantTime
                .minusMinutes(currantTime.getMinute())
                .minusSeconds(currantTime.getSecond())
                .minusNanos(currantTime.getNano());

        Set<LocalDateTime> garagePlaceAvailableTime = new TreeSet<>();
        garagePlaceDAO.getGaragePlaces().forEach(garagePlace -> {
            garagePlace.getSchedule().forEach((timeAppointment, isAvailableAppointment) -> {
                if (timeAppointment.isAfter(searchTime) && isAvailableAppointment) {
                    garagePlaceAvailableTime.add(timeAppointment);
                }
            });
        });

        Set<LocalDateTime> craftsmanAvailableTime = new TreeSet<>();
        craftsmanDAO.getCraftsmen().forEach(craftsman -> {
            craftsman.getSchedule().forEach((timeAppointment, isAvailableAppointment) -> {
                if (timeAppointment.isAfter(searchTime) && isAvailableAppointment) {
                    craftsmanAvailableTime.add(timeAppointment);
                }
            });
        });

        for (LocalDateTime time : garagePlaceAvailableTime) {
            if (craftsmanAvailableTime.contains(time)) {
                return time;
            }
        }
        return null;
    }
}