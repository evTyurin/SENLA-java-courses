package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.dao.IGaragePlaceDao;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.service.IGaragePlaceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:test-application.properties")
class GaragePlaceServiceUnitTest {

    @Autowired
    IGaragePlaceService garagePlaceService;
    @Autowired
    IGaragePlaceDao garagePlaceDao;
    List<GaragePlace> testGaragePlaces;
    LocalDateTime testLocalDateTime;
    long testId;

    @BeforeEach
    void init() {
        GaragePlace testGaragePlace1 = new GaragePlace();
        testGaragePlace1.setId(1L);
        testGaragePlace1.setNumber(1);
        testGaragePlace1.setSpace(15.0);

        GaragePlace testGaragePlace2 = new GaragePlace();
        testGaragePlace2.setId(2L);
        testGaragePlace2.setNumber(2);
        testGaragePlace2.setSpace(10.0);

        testGaragePlaces = new ArrayList<>(Arrays
                .asList(testGaragePlace1,
                        testGaragePlace2));

        testLocalDateTime = LocalDateTime
                .of(2023, 10, 10, 12, 0);

        testId = 1L;
    }

    @AfterEach
    void clearInvocationsInMocked() {
        Mockito.clearInvocations(garagePlaceDao);
    }

    @Test
    void delete_deleteGaragePlaceById_deletedSuccessfully() throws NotFoundException {
        GaragePlace testGaragePlace = testGaragePlaces
                .stream()
                .filter(garagePlace -> garagePlace.getId() == testId)
                .findFirst()
                .get();

        when(garagePlaceDao.findById(testId)).thenReturn(testGaragePlace);

        garagePlaceService.removeGaragePlace(testId);

        verify(garagePlaceDao).delete(testGaragePlace);
    }

    @Test
    void create_createGaragePlace_createSuccessfully() {
        GaragePlace testGaragePlace = new GaragePlace();
        testGaragePlace.setNumber(10);
        testGaragePlace.setSpace(25.0);

        garagePlaceService.addGaragePlace(testGaragePlace);

        verify(garagePlaceDao).create(testGaragePlace);
    }

    @Test
    void find_findAvailableGaragePlaces_returnedGaragePlaces() {
        when(garagePlaceDao.getAvailableGaragePlaces()).thenReturn(testGaragePlaces);
        List<GaragePlace> garagePlacesReturned = garagePlaceService.getAvailablePlaces();

        assertEquals(testGaragePlaces, garagePlacesReturned);

        verify(garagePlaceDao).getAvailableGaragePlaces();
    }

    @Test
    void find_findAvailableGaragePlacesAmount_returnedAmount() {
        when(garagePlaceDao.getAvailablePlacesAmount(testLocalDateTime))
                .thenReturn(2L);

        garagePlaceService.getAvailablePlacesAmount(testLocalDateTime);

        verify(garagePlaceDao).getAvailablePlacesAmount(testLocalDateTime);
    }

    @Test
    void find_findNearestAvailableDate_returnedNearestAvailableDate() {
        when(garagePlaceDao.getAvailablePlacesAmount(testLocalDateTime)).thenReturn(2L);
        long availablePlacesAmount = garagePlaceService.getAvailablePlacesAmount(testLocalDateTime);

        assertEquals(2, availablePlacesAmount);

        verify(garagePlaceDao).getAvailablePlacesAmount(testLocalDateTime);
    }

    @Test
    void find_findGaragePlaceById_returnedGaragePlace() throws NotFoundException {
        GaragePlace testGaragePlace = testGaragePlaces
                .stream()
                .filter(garagePlace -> garagePlace.getId() == testId)
                .findFirst()
                .get();

        when(garagePlaceDao.findById(testId)).thenReturn(testGaragePlace);
        GaragePlace garagePlaceReturned = garagePlaceService.getGaragePlaceById(testId);

        assertEquals(garagePlaceReturned, testGaragePlace);

        verify(garagePlaceDao).findById(testId);
    }
}
