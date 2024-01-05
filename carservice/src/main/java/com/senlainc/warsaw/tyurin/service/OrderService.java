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

@Service
public class OrderService implements IOrderService{

    private final static Logger logger = Logger.getLogger(OrderService.class);

    private IOrderDao orderDao;
    @Value("${order.shift-completion-time.enabled}")
    private boolean isCompletionDateTimeShiftable;
    @Value("${order.remove.enabled}")
    private boolean isOrderRemovable;

    @Autowired
    public OrderService(IOrderDao orderDao) {
        this.orderDao = orderDao;
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
            return getCurrentlyExecutedOrdersSortedByCompletionDate();
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
    @Transactional
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

    private List<Order> getSortedBySubmissionDate() {
        return orderDao.getOrdersSubmissionDateSorted();
    }

    private List<Order> getSortedByCompletionDate() {
        return orderDao.getOrdersCompletionDateSorted();
    }

    private List<Order> getSortedByStartDate() {
        return orderDao.getOrdersStartDateSorted();
    }

    private List<Order> getSortedByPrice() {
        return orderDao.getOrdersPriceSorted();
    }

    private List<Order> getCurrentlyExecutedOrdersSortedBySubmissionDate() {
        return orderDao.getInProgressOrdersSubmissionDateSorted();
    }

    private List<Order> getCurrentlyExecutedOrdersSortedByCompletionDate() {
        return orderDao.getInProgressOrdersCompletionDateSorted();
    }

    private List<Order> getCurrentlyExecutedOrdersSortedByPrice() {
        return orderDao.getInProgressOrdersPriceSorted();
    }

    private List<Order> getArchivedOrdersSortedBySubmissionDate() {
        return orderDao.getArchivedOrdersSubmissionDateSorted();
    }

    private List<Order> getArchivedOrdersSortedByCompletionDate() {
        return orderDao.getArchivedOrdersCompletionDateSorted();
    }

    private List<Order> getArchivedOrdersSortedByPrice() {
        return orderDao.getArchivedOrdersPriceSorted();
    }

    private void changeStatus(long id, OrderStatus status) throws NotFoundException {
        Order order = orderDao.findById(id);
        order.setOrderStatus(status);
        orderDao.update(order);
    }

    private void shiftStartDateTime(long id, LocalDateTime startDateTime) throws NotFoundException {
        Order order = orderDao.findById(id);
        order.setStartDate(startDateTime);
        orderDao.update(order);
    }

    private void shiftCompletionDateTime(long id, LocalDateTime completionDateTime) throws NotFoundException {
        if (isCompletionDateTimeShiftable) {
            Order order = orderDao.findById(id);
            order.setCompletionDate(completionDateTime);
            orderDao.update(order);
        } else {
            logger.error("Shifting completion time was prohibited");
        }
    }
}
