package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.IGaragePlaceDao;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GaragePlaceService implements IGaragePlaceService{

    private final static Logger logger = Logger.getLogger(GaragePlaceService.class);

    private IGaragePlaceDao garagePlaceDao;
    @Value("${garage-place.add.enabled}")
    private boolean isGaragePlaceAddable;
    @Value("${garage-place.remove.enabled}")
    private boolean isGaragePlaceRemovable;

    @Autowired
    public GaragePlaceService(IGaragePlaceDao garagePlaceDao) {
        this.garagePlaceDao = garagePlaceDao;
    }

    @Override
    public void addGaragePlace(GaragePlace garagePlace) {

        if (isGaragePlaceAddable) {
            garagePlaceDao.create(garagePlace);
        } else {
            logger.error("Adding garage places was prohibited");
        }
    }

    @Override
    public void removeGaragePlace(long id) throws NotFoundException {

        if (isGaragePlaceRemovable) {
            garagePlaceDao.delete(garagePlaceDao.findById(id));
        } else {
            logger.error("Removing garage places was prohibited");
        }
    }

    @Override
    public List<GaragePlace> getAvailablePlaces() {
        return garagePlaceDao.getAvailableGaragePlaces();
    }

    @Override
    public long getAvailablePlacesAmount(LocalDateTime localDateTime) {
        return garagePlaceDao.getAvailablePlacesAmount(localDateTime);
    }

    @Override
    public LocalDateTime getNearestAvailableDate() {
        LocalDateTime currantTime = LocalDateTime.now();

        LocalDateTime searchTime = currantTime
                .minusMinutes(currantTime.getMinute())
                .minusSeconds(currantTime.getSecond())
                .minusNanos(currantTime.getNano())
                .plusHours(1);

        while (getAvailablePlacesAmount(searchTime) == 0) {
            searchTime = searchTime.plusHours(1);
        }
        return searchTime;
    }

    @Override
    public GaragePlace getGaragePlaceById(Long id) throws NotFoundException {
        return garagePlaceDao.findById(id);
    }
}