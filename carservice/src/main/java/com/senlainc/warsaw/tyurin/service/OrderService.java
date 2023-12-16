package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.IOrderDao;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.util.OrderStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService{

    private final static Logger logger = Logger.getLogger(OrderService.class);

    @Autowired
    private IOrderDao orderDao;
    @Autowired
    private ICraftsmanService craftsmanService;
    @Autowired
    private IGaragePlaceService garagePlaceService;
    @Value("${order.shift-completion-time.enabled}")
    private boolean isCompletionDateTimeShiftable;
    @Value("${order.remove.enabled}")
    private boolean isOrderRemovable;

    @Override
    public void changeStatus(long id, OrderStatus status) {
        Order order = orderDao.findById(id);
        order.setOrderStatus(status);
        orderDao.update(order);
    }

    @Override
    public void shiftStartDateTime(long id, LocalDateTime startDateTime) {
        Order order = orderDao.findById(id);
        order.setStartDate(startDateTime);
        orderDao.update(order);
    }

    @Override
    public void shiftCompletionDateTime(long id, LocalDateTime completionDateTime) {
        if (isCompletionDateTimeShiftable) {
            Order order = orderDao.findById(id);
            order.setCompletionDate(completionDateTime);
            orderDao.update(order);
        } else {
            logger.error("Shifting completion time was prohibited");
        }
    }

    @Override
    public List<Order> getSortedBySubmissionDate() {
        return orderDao.getOrdersSubmissionDateSorted();
    }

    @Override
    public List<Order> getSortedByCompletionDate() {
        return orderDao.getOrdersCompletionDateSorted();
    }

    @Override
    public List<Order> getSortedByStartDate() {
        return orderDao.getOrdersStartDateSorted();
    }

    @Override
    public List<Order> getSortedByPrice() {
        return orderDao.getOrdersPriceSorted();
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedBySubmissionDate() {
        return orderDao.getInProgressOrdersSubmissionDateSorted();
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedByCompletionDate() {
        return orderDao.getInProgressOrdersCompletionDateSorted();
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedByPrice() {
        return orderDao.getInProgressOrdersStartDateSorted();
    }

    @Override
    public List<Order> getArchivedOrdersSortedBySubmissionDate() {
        return orderDao.getArchivedOrdersSubmissionDateSorted();
    }

    @Override
    public List<Order> getArchivedOrdersSortedByCompletionDate() {
        return orderDao.getArchivedOrdersCompletionDateSorted();
    }

    @Override
    public List<Order> getArchivedOrdersSortedByPrice() {
        return orderDao.getArchivedOrdersPriceSorted();
    }

    @Override
    public Order getOrderByCraftsmen(long craftsmanId) {
        return orderDao.getOrderByCraftsmen(craftsmanId);
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getAll();
    }

    @Override
    public Order createOrder(double price,
                             LocalDateTime startDate,
                             LocalDateTime completionDate,
                             List<Long> craftsmenId,
                             long garagePlaceId) {
        Order order = new Order();
        order.setPrice(price);
        order.setStartDate(startDate);
        order.setCompletionDate(completionDate);
        order.setCraftsmen(craftsmenId
                .stream()
                .map(craftsmanId -> craftsmanService
                        .getCraftsmanById(craftsmanId))
                .collect(Collectors.toList()));
        try {
            order.setGaragePlace(garagePlaceService.getGaragePlaceById(garagePlaceId));        } catch (Exception exception) {
            logger.error("Can't get garage place", exception);
        }
        return order;
    }

    @Override
    public void addOrder(Order order) {
        orderDao.create(order);
    }

    @Override
    public Order getOrderById(long id) {
        return orderDao.findById(id);
    }

    @Override
    public void removeOrder(Long id) {

        if (isOrderRemovable) {
            orderDao.delete(orderDao.findById(id));
        } else {
            logger.error("Removing order was prohibited");
        }
    }
}
