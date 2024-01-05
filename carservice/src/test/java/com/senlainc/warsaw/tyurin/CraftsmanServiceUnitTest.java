package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.dao.ICraftsmanDao;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.exception.ExpectationFailedException;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.service.ICraftsmanService;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:test-application.properties")
class CraftsmanServiceUnitTest {

    @Autowired
    ICraftsmanDao craftsmanDao;
    @Autowired
    ICraftsmanService craftsmanService;
    List<Craftsman> testCraftsmen;
    long testId;

    @BeforeEach
    void init() {
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
        testCraftsmen = new ArrayList<>(Arrays
                .asList(testCraftsman1,
                        testCraftsman2,
                        testCraftsman3,
                        testCraftsman4));
        testId = 1L;
    }

    @AfterEach
    void clearInvocationsInMocked() {
        Mockito.clearInvocations(craftsmanDao);
    }

    @Test
    void find_findCraftsmanById_returnedCraftsman() throws NotFoundException {
        Craftsman testCraftsman = testCraftsmen
                .stream()
                .filter(craftsman -> craftsman.getId() == testId)
                .findFirst()
                .get();
        when(craftsmanDao.findById(testId)).thenReturn(testCraftsman);
        Craftsman craftsmanReturned = craftsmanService.getCraftsmanById(testId);

        assertEquals(craftsmanReturned, testCraftsman);

        verify(craftsmanDao).findById(testId);
    }

    @Test
    void find_findAllCraftsmen_returnedCraftsmen() {
        when(craftsmanDao.getAll()).thenReturn(testCraftsmen);
        List<Craftsman> craftsmenReturned = craftsmanService.getCraftsmen();

        assertEquals(testCraftsmen, craftsmenReturned);

        verify(craftsmanDao).getAll();
    }

    @Test
    void delete_deleteCraftsmanById_deletedSuccessfully() throws NotFoundException {
        Craftsman testCraftsman = testCraftsmen
                .stream()
                .filter(craftsman -> craftsman.getId() == testId)
                .findFirst()
                .get();

        when(craftsmanDao.findById(testId)).thenReturn(testCraftsman);

        craftsmanService.removeCraftsmanById(testId);

        verify(craftsmanDao).delete(testCraftsman);
    }

    @Test
    void create_createCraftsman_createSuccessfully() {
        Craftsman testCraftsman = new Craftsman();
        testCraftsman.setName("testName");
        testCraftsman.setSurname("testSurname");

        craftsmanService.addCraftsman(testCraftsman);

        verify(craftsmanDao).create(testCraftsman);
    }

    @Test
    void find_findCraftsmenByOrderId_returnedCraftsmen() {
        when(craftsmanDao.getCraftsmenByOrder(testId)).thenReturn(testCraftsmen);
        List<Craftsman> craftsmenReturned = craftsmanService.getCraftsmenByOrder(1L);

        assertEquals(testCraftsmen, craftsmenReturned);

        verify(craftsmanDao).getCraftsmenByOrder(testId);
    }

    @Test
    void find_findCraftsmenSortedByCriteria_returnedCraftsmenSortedByBusyness() throws ExpectationFailedException {
        when(craftsmanDao.getSortedByBusyness()).thenReturn(testCraftsmen);
        List<Craftsman> craftsmenReturned = craftsmanService.getSortedByCriteria("business");

        assertEquals(testCraftsmen, craftsmenReturned);

        verify(craftsmanDao).getSortedByBusyness();
    }

    @Test
    void find_findCraftsmenSortedByCriteria_returnedCraftsmenSortedAlphabetically() throws ExpectationFailedException {
        when(craftsmanDao.getSortedAlphabetically()).thenReturn(testCraftsmen);
        List<Craftsman> craftsmenReturned = craftsmanService.getSortedByCriteria("alphabetically");

        assertEquals(testCraftsmen, craftsmenReturned);

        verify(craftsmanDao).getSortedAlphabetically();
    }
}
