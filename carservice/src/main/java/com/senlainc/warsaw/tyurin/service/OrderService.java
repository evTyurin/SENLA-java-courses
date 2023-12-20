package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.IOrderDao;
import com.senlainc.warsaw.tyurin.entity.Order;
import com.senlainc.warsaw.tyurin.exception.ExpectationFailedException;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.util.OrderStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    @Transactional
    public void changeStatus(long id, OrderStatus status) throws NotFoundException {
        Order order = orderDao.findById(id);
        order.setOrderStatus(status);
        orderDao.update(order);
    }

    @Override
    @Transactional
    public void shiftStartDateTime(long id, LocalDateTime startDateTime) throws NotFoundException {
        Order order = orderDao.findById(id);
        order.setStartDate(startDateTime);
        orderDao.update(order);
    }

    @Override
    @Transactional
    public void shiftCompletionDateTime(long id, LocalDateTime completionDateTime) throws NotFoundException {
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
                .map(craftsmanId -> {
                    try {
                        return craftsmanService
                                .getCraftsmanById(craftsmanId);
                    } catch (NotFoundException exception) {
                        logger.error("Can't get craftsman with id " + craftsmanId, exception);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
        try {
            order.setGaragePlace(garagePlaceService.getGaragePlaceById(garagePlaceId));        } catch (Exception exception) {
            logger.error("Can't get garage place", exception);
        }
        return order;
    }

    @Override
    public void addOrder(Order order) throws NotFoundException {
        orderDao.create(order);
    }

    @Override
    public Order getOrderById(long id) throws NotFoundException {
        return orderDao.findById(id);
    }

    @Override
    public void removeOrder(Long id) throws NotFoundException {

        if (isOrderRemovable) {
            orderDao.delete(orderDao.findById(id));
        } else {
            logger.error("Removing order was prohibited");
        }
    }

    @Override
    public List<Order> getArchivedOrdersSortByCriteria(String criteria) throws ExpectationFailedException {
        if (criteria.equals("price")) {
            return getArchivedOrdersSortedByPrice();
        } else if (criteria.equals("completion-date")) {
            return getArchivedOrdersSortedByCompletionDate();
        } else if (criteria.equals("submission-date")) {
            return getArchivedOrdersSortedBySubmissionDate();
        }
        throw new ExpectationFailedException(criteria);
    }

    @Override
    public List<Order> getCurrentlyExecutedOrdersSortedByCriteria(String criteria) throws ExpectationFailedException {
        if (criteria.equals("price")) {
            return getCurrentlyExecutedOrdersSortedByPrice();
        } else if (criteria.equals("completion-date")) {
            return getArchivedOrdersSortedByCompletionDate();
        } else if (criteria.equals("submission-date")) {
            return getCurrentlyExecutedOrdersSortedBySubmissionDate();
        }
        throw new ExpectationFailedException(criteria);
    }

    @Override
    public List<Order> getSortedAllOrdersByCriteria(String criteria) throws ExpectationFailedException {
        if (criteria.equals("price")) {
            return getSortedByPrice();
        } else if (criteria.equals("completion-date")) {
            return getSortedByCompletionDate();
        } else if (criteria.equals("submission-date")) {
            return getSortedBySubmissionDate();
        } else if (criteria.equals("start-date")) {
            return getSortedByStartDate();
        }
        throw new ExpectationFailedException(criteria);
    }

    @Override
    public void updateOrder(Order order) throws NotFoundException {
        if (order.getId() < 1) {
            throw new NotFoundException(order.getId());
        }
        if (order.getOrderStatus() != OrderStatus.NEW) {
            changeStatus(order.getId(), order.getOrderStatus());
        }
        if (order.getStartDate() != null) {
            shiftStartDateTime(order.getId(), order.getStartDate());
        }
        if (order.getCompletionDate() != null) {
            shiftCompletionDateTime(order.getId(), order.getCompletionDate());
        }
    }
}
