package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.dao.IGaragePlaceDao;
import com.senlainc.warsaw.tyurin.dao.IOrderDao;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.exception.ExpectationFailedException;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.service.ICraftsmanService;
import com.senlainc.warsaw.tyurin.service.IOrderService;
import com.senlainc.warsaw.tyurin.util.OrderStatus;
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
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:test-application.properties")
class OrderServiceUnitTest {

    @Autowired
    IOrderDao orderDao;
    @Autowired
    IOrderService orderService;
    @Autowired
    IGaragePlaceDao garagePlaceDao;
    @Autowired
    ICraftsmanService craftsmanService;
    List<Order> testOrders;
    LocalDateTime testLocalDateTime;
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

        Order testOrder1 = new Order();
        testOrder1.setId(1);
        testOrder1.setPrice(100);
        testOrder1.setCraftsmen(new ArrayList<>(Arrays
                .asList(testCraftsman1,
                        testCraftsman2)));
        Order testOrder2 = new Order();
        testOrder2.setId(2);
        testOrder2.setPrice(200);
        testOrder2.setCraftsmen(new ArrayList<>(Arrays
                .asList(testCraftsman3,
                        testCraftsman4)));

        testOrders = new ArrayList<>(Arrays
                .asList(testOrder1, testOrder2));

        testLocalDateTime = LocalDateTime
                .of(2023, 10, 10, 12, 0);

        testId = 1L;
    }

    @AfterEach
    void clearInvocationsInMocked() {
        Mockito.clearInvocations(orderDao);
    }

    @Test
    void update_updateOrderStatus_updatedSuccessfully() throws NotFoundException {
        Order testOrder = testOrders
                .stream()
                .filter(order -> order.getId() == testId)
                .findFirst()
                .get();

        testOrder.setOrderStatus(OrderStatus.COMPLETED);
        when(orderDao.findById(testId)).thenReturn(testOrder);

        orderService.updateOrder(testOrder);
        Order orderReturned = orderService.getOrderById(testId);
        orderReturned.setOrderStatus(OrderStatus.COMPLETED);
        orderDao.update(orderReturned);

        verify(orderDao, times(2)).findById(testId);
        verify(orderDao, times(2)).update(testOrder);
    }

    @Test
    void update_updateOrderStartDate_updatedSuccessfully() throws NotFoundException {
        Order testOrder = testOrders
                .stream()
                .filter(order -> order.getId() == testId)
                .findFirst()
                .get();

        testOrder.setStartDate(testLocalDateTime);
        when(orderDao.findById(testId)).thenReturn(testOrder);

        orderService.updateOrder(testOrder);
        Order orderReturned = orderService.getOrderById(testId);
        orderReturned.setStartDate(testLocalDateTime);
        orderDao.update(orderReturned);

        verify(orderDao, times(2)).findById(testId);
        verify(orderDao, times(2)).update(testOrder);
    }

    @Test
    void update_updateOrderCompletionDate_updatedSuccessfully() throws NotFoundException {
        Order testOrder = testOrders
                .stream()
                .filter(order -> order.getId() == testId)
                .findFirst()
                .get();

        testOrder.setCompletionDate(testLocalDateTime);
        when(orderDao.findById(testId)).thenReturn(testOrder);

        orderService.updateOrder(testOrder);
        Order orderReturned = orderService.getOrderById(testId);
        orderReturned.setCompletionDate(testLocalDateTime);
        orderDao.update(orderReturned);

        verify(orderDao, times(2)).findById(testId);
        verify(orderDao, times(2)).update(testOrder);
    }

    @Test
    void find_findOrdersSortedByCriteria_returnedOrdersSortedBySubmissionDate() throws ExpectationFailedException {
        when(orderDao.getOrdersSubmissionDateSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getSortedAllOrdersByCriteria("submission-date");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getOrdersSubmissionDateSorted();
    }

    @Test
    void find_findOrdersSortedByCriteria_returnedOrdersSortedByCompletionDate() throws ExpectationFailedException {
        when(orderDao.getOrdersCompletionDateSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getSortedAllOrdersByCriteria("completion-date");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getOrdersCompletionDateSorted();
    }

    @Test
    void find_findOrdersSortedByCriteria_returnedOrdersSortedByPrice() throws ExpectationFailedException {
        when(orderDao.getOrdersPriceSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getSortedAllOrdersByCriteria("price");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getOrdersPriceSorted();
    }

    @Test
    void find_findOrdersSortedByCriteria_returnedOrdersSortedByStartDate() throws ExpectationFailedException {
        when(orderDao.getOrdersStartDateSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getSortedAllOrdersByCriteria("start-date");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getOrdersStartDateSorted();
    }

    @Test
    void find_findCurrentlyExecutedOrdersSortedByCriteria_returnedOrdersSortedBySubmissionDate() throws ExpectationFailedException {
        when(orderDao.getInProgressOrdersSubmissionDateSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getCurrentlyExecutedOrdersSortedByCriteria("submission-date");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getInProgressOrdersSubmissionDateSorted();
    }

    @Test
    void find_findCurrentlyExecutedOrdersSortedByCriteria_returnedOrdersSortedByCompletionDate() throws ExpectationFailedException {
        when(orderDao.getInProgressOrdersCompletionDateSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getCurrentlyExecutedOrdersSortedByCriteria("completion-date");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getInProgressOrdersCompletionDateSorted();
    }

    @Test
    void find_findCurrentlyExecutedOrdersSortedByCriteria_returnedOrdersSortedByPrice() throws ExpectationFailedException {
        when(orderDao.getInProgressOrdersPriceSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getCurrentlyExecutedOrdersSortedByCriteria("price");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getInProgressOrdersPriceSorted();
    }

    @Test
    void find_findArchivedOrdersSortedByCriteria_returnedOrdersSortedByPrice() throws ExpectationFailedException {
        when(orderDao.getArchivedOrdersPriceSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getArchivedOrdersSortByCriteria("price");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getArchivedOrdersPriceSorted();
    }

    @Test
    void find_findArchivedOrdersSortedByCriteria_returnedOrdersSortedBySubmissionDate() throws ExpectationFailedException {
        when(orderDao.getArchivedOrdersSubmissionDateSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getArchivedOrdersSortByCriteria("submission-date");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getArchivedOrdersSubmissionDateSorted();
    }

    @Test
    void find_findArchivedOrdersSortedByCriteria_returnedOrdersSortedByCompletionDate() throws ExpectationFailedException {
        when(orderDao.getArchivedOrdersCompletionDateSorted()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getArchivedOrdersSortByCriteria("completion-date");

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getArchivedOrdersCompletionDateSorted();
    }

    @Test
    void find_findOrdersByCraftsmanId_returnedOrders()  {
        Order testOrder = testOrders
                .stream()
                .filter(order -> order.getId() == testId)
                .findFirst()
                .get();

        when(orderDao.getOrderByCraftsmen(testId)).thenReturn(testOrder);
        Order orderReturned = orderService.getOrderByCraftsmen(testId);

        assertEquals(testOrder, orderReturned);

        verify(orderDao).getOrderByCraftsmen(testId);
    }

    @Test
    void find_findAllOrders_returnedOrders()  {
        when(orderDao.getAll()).thenReturn(testOrders);
        List<Order> ordersReturned = orderService.getOrders();

        assertEquals(testOrders, ordersReturned);

        verify(orderDao).getAll();
    }

    @Test
    void create_createOrder_createSuccessfully() throws NotFoundException {
        Order testOrder = new Order();

        orderService.addOrder(testOrder);

        verify(orderDao).create(testOrder);
    }

    @Test
    void delete_deleteOrderById_deletedSuccessfully() throws NotFoundException {
        Order testOrder = testOrders
                .stream()
                .filter(order -> order.getId() == testId)
                .findFirst()
                .get();

        when(orderDao.findById(testId)).thenReturn(testOrder);

        orderService.removeOrder(testId);

        verify(orderDao).findById(testId);
        verify(orderDao).delete(testOrder);
    }

    @Test
    void find_findOrderById_returnedOrder() throws NotFoundException {
        Order testOrder = new Order();
        testOrder.setId(testId);
        when(orderDao.findById(testId)).thenReturn(testOrder);

        Order orderReturned = orderService.getOrderById(testId);

        assertEquals(orderReturned, testOrder);

        verify(orderDao).findById(testId);
    }
}
