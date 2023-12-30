package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.dao.IGaragePlaceDao;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.service.GaragePlaceService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GaragePlaceServiceUnitTest {

    @InjectMocks
    private GaragePlaceService garagePlaceService;
    @Mock
    private IGaragePlaceDao garagePlaceDao;

    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(garagePlaceDao);
    }

    @Test
    void delete_deleteGaragePlaceById_deletedSuccessfully() throws NotFoundException {
        GaragePlace testGaragePlace = new GaragePlace();
        testGaragePlace.setId(1L);
        testGaragePlace.setNumber(1);
        testGaragePlace.setSpace(15.0);

        ReflectionTestUtils.setField(garagePlaceService,"isGaragePlaceRemovable",true);
        when(garagePlaceDao.findById(1L)).thenReturn(testGaragePlace);

        garagePlaceService.removeGaragePlace(1L);

        verify(garagePlaceDao).delete(testGaragePlace);
    }

    @Test
    void find_findAvailableGaragePlaces_returnedGaragePlaces() {
        GaragePlace testGaragePlace1 = new GaragePlace();
        testGaragePlace1.setId(1L);
        testGaragePlace1.setNumber(1);
        testGaragePlace1.setSpace(15.0);

        GaragePlace testGaragePlace2 = new GaragePlace();
        testGaragePlace2.setId(2L);
        testGaragePlace2.setNumber(2);
        testGaragePlace2.setSpace(10.0);

        List<GaragePlace> testGaragePlaces = new ArrayList<>(Arrays
                .asList(testGaragePlace1,
                        testGaragePlace2));
        when(garagePlaceDao.getAvailableGaragePlaces()).thenReturn(testGaragePlaces);
        List<GaragePlace> garagePlacesReturned = garagePlaceService.getAvailablePlaces();

        assertEquals(testGaragePlaces, garagePlacesReturned);

        verify(garagePlaceDao).getAvailableGaragePlaces();
    }

    @Test
    void find_findAvailableGaragePlacesAmount_returnedAmount() {
        when(garagePlaceDao.getAvailablePlacesAmount(LocalDateTime
                .of(2023, 10, 10, 12, 0)))
                .thenReturn(2L);

        garagePlaceService.getAvailablePlacesAmount(LocalDateTime
                .of(2023, 10, 10, 12, 0));

        verify(garagePlaceDao).getAvailablePlacesAmount(LocalDateTime
                .of(2023, 10, 10, 12, 0));
    }

    @Test
    void find_findNearestAvailableDate_returnedNearestAvailableDate() {
        when(garagePlaceDao.getAvailablePlacesAmount(LocalDateTime
                .of(2023, 10, 10, 12, 0))).thenReturn(2L);
        long availablePlacesAmount = garagePlaceService.getAvailablePlacesAmount(LocalDateTime
                .of(2023, 10, 10, 12, 0));

        assertEquals(2, availablePlacesAmount);

        verify(garagePlaceDao).getAvailablePlacesAmount(LocalDateTime
                .of(2023, 10, 10, 12, 0));
    }

    @Test
    void find_findGaragePlaceById_returnedGaragePlace() throws NotFoundException {
        GaragePlace testGaragePlace = new GaragePlace();
        testGaragePlace.setId(1L);
        testGaragePlace.setNumber(1);
        testGaragePlace.setSpace(15.0);

        when(garagePlaceDao.findById(1)).thenReturn(testGaragePlace);
        GaragePlace garagePlaceReturned = garagePlaceService.getGaragePlaceById(1L);

        assertEquals(garagePlaceReturned, testGaragePlace);

        verify(garagePlaceDao).findById(1L);
    }

}
