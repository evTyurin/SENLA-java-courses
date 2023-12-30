package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.dao.ICraftsmanDao;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.service.CraftsmanService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CraftsmanServiceUnitTest {

    @InjectMocks
    private CraftsmanService craftsmanService;
    @Mock
    private ICraftsmanDao craftsmanDao;

    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(craftsmanDao);
    }

    @Test
    void find_findCraftsmanById_returnedCraftsman() throws NotFoundException {
        Craftsman testCraftsman = new Craftsman();
        testCraftsman.setId(2L);
        testCraftsman.setName("Jim");
        testCraftsman.setSurname("Bo");
        when(craftsmanDao.findById(2)).thenReturn(testCraftsman);
        Craftsman craftsmanReturned = craftsmanService.getCraftsmanById(2L);

        assertEquals(craftsmanReturned, testCraftsman);

        verify(craftsmanDao).findById(2L);
    }

    @Test
    void find_findAllCraftsmen_returnedCraftsmen() {
        Craftsman testCraftsman1 = new Craftsman();
        testCraftsman1.setId(1L);
        testCraftsman1.setName("John");
        testCraftsman1.setSurname("Constantine");
        Craftsman testCraftsman2 = new Craftsman();
        testCraftsman2.setId(2L);
        testCraftsman2.setName("Jim");
        testCraftsman2.setSurname("Bo");
        Craftsman testCraftsman3 = new Craftsman();
        testCraftsman3.setId(3L);
        testCraftsman3.setName("Bot");
        testCraftsman3.setSurname("Cinnic");
        Craftsman testCraftsman4 = new Craftsman();
        testCraftsman4.setId(4L);
        testCraftsman4.setName("Clark");
        testCraftsman4.setSurname("Kent");
        List<Craftsman> testCraftsmen = new ArrayList<>(Arrays
                .asList(testCraftsman1,
                        testCraftsman2,
                        testCraftsman3,
                        testCraftsman4));
        when(craftsmanDao.getAll()).thenReturn(testCraftsmen);
        List<Craftsman> craftsmenReturned = craftsmanService.getCraftsmen();

        assertEquals(testCraftsmen, craftsmenReturned);

        verify(craftsmanDao).getAll();
    }

    @Test
    void delete_deleteCraftsmanById_deletedSuccessfully() throws NotFoundException {
        Craftsman testCraftsman = new Craftsman();
        testCraftsman.setId(1L);
        testCraftsman.setName("John");
        testCraftsman.setSurname("Constantine");

        when(craftsmanDao.findById(1L)).thenReturn(testCraftsman);

        craftsmanService.removeCraftsmanById(1L);

        verify(craftsmanDao).delete(testCraftsman);
    }

    @Test
    void find_findCraftsmenSortedAlphabetically_returnedCraftsmen() {
        Craftsman testCraftsman1 = new Craftsman();
        testCraftsman1.setId(1L);
        testCraftsman1.setName("John");
        testCraftsman1.setSurname("Constantine");
        Craftsman testCraftsman2 = new Craftsman();
        testCraftsman2.setId(2L);
        testCraftsman2.setName("Jim");
        testCraftsman2.setSurname("Bo");
        Craftsman testCraftsman3 = new Craftsman();
        testCraftsman3.setId(3L);
        testCraftsman3.setName("Bot");
        testCraftsman3.setSurname("Cinnic");
        Craftsman testCraftsman4 = new Craftsman();
        testCraftsman4.setId(4L);
        testCraftsman4.setName("Clark");
        testCraftsman4.setSurname("Kent");
        List<Craftsman> testCraftsmen = new ArrayList<>(Arrays
                .asList(testCraftsman2,
                        testCraftsman3,
                        testCraftsman1,
                        testCraftsman4));
        when(craftsmanDao.getSortedAlphabetically()).thenReturn(testCraftsmen);
        List<Craftsman> craftsmenReturned = craftsmanService.getSortedAlphabetically();

        assertEquals(testCraftsmen, craftsmenReturned);

        verify(craftsmanDao).getSortedAlphabetically();
    }

    @Test
    void find_findCraftsmenSortedByBusiness_returnedCraftsmen() {
        Craftsman testCraftsman1 = new Craftsman();
        testCraftsman1.setId(1L);
        testCraftsman1.setName("John");
        testCraftsman1.setSurname("Constantine");
        Craftsman testCraftsman2 = new Craftsman();
        testCraftsman2.setId(2L);
        testCraftsman2.setName("Jim");
        testCraftsman2.setSurname("Bo");
        Craftsman testCraftsman3 = new Craftsman();
        testCraftsman3.setId(3L);
        testCraftsman3.setName("Bot");
        testCraftsman3.setSurname("Cinnic");
        Craftsman testCraftsman4 = new Craftsman();
        testCraftsman4.setId(4L);
        testCraftsman4.setName("Clark");
        testCraftsman4.setSurname("Kent");
        List<Craftsman> testCraftsmen = new ArrayList<>(Arrays
                .asList(testCraftsman2,
                        testCraftsman3,
                        testCraftsman4,
                        testCraftsman1));
        when(craftsmanDao.getSortedByBusyness()).thenReturn(testCraftsmen);
        List<Craftsman> craftsmenReturned = craftsmanService.getSortedByBusyness();

        assertEquals(testCraftsmen, craftsmenReturned);

        verify(craftsmanDao).getSortedByBusyness();
    }

    @Test
    void find_findCraftsmenByOrderId_returnedCraftsmen() {
        Craftsman testCraftsman1 = new Craftsman();
        testCraftsman1.setId(1L);
        testCraftsman1.setName("John");
        testCraftsman1.setSurname("Constantine");
        Craftsman testCraftsman2 = new Craftsman();
        testCraftsman2.setId(2L);
        testCraftsman2.setName("Jim");
        testCraftsman2.setSurname("Bo");
        List<Craftsman> testCraftsmen = new ArrayList<>(Arrays
                .asList(testCraftsman1,
                        testCraftsman2));
        when(craftsmanDao.getCraftsmenByOrder(1L)).thenReturn(testCraftsmen);
        List<Craftsman> craftsmenReturned = craftsmanService.getCraftsmenByOrder(1L);

        assertEquals(testCraftsmen, craftsmenReturned);

        verify(craftsmanDao).getCraftsmenByOrder(1L);
    }
}
