package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.entity.Order;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import javax.persistence.criteria.*;
import java.util.Collections;
import java.util.List;

@DependencyClass
public class OrderDao extends AbstractGenericDao<Order> implements IOrderDao {

    private final static Logger logger = Logger.getLogger(OrderDao.class);

    @Override
    public List<Order> getOrdersPriceSorted() {
        return getAllOrdersSortedByCriteria("price");
    }

    @Override
    public List<Order> getOrdersSubmissionDateSorted() {
        return getAllOrdersSortedByCriteria("submission_date");
    }

    @Override
    public List<Order> getOrdersCompletionDateSorted() {
        return getAllOrdersSortedByCriteria("completion_date");
    }

    @Override
    public List<Order> getOrdersStartDateSorted() {
        return getAllOrdersSortedByCriteria("start_date");
    }

    @Override
    public List<Order> getInProgressOrdersSubmissionDateSorted() {
        return getOrdersInProgressSortedByCriteria("submission_date");
    }

    @Override
    public List<Order> getInProgressOrdersCompletionDateSorted() {
        return getOrdersInProgressSortedByCriteria("completion_date");
    }

    @Override
    public List<Order> getInProgressOrdersStartDateSorted() {
        return getOrdersInProgressSortedByCriteria("start_date");
    }

    @Override
    public List<Order> getArchivedOrdersSubmissionDateSorted() {
        return getAllOrdersSortedByCriteria("submission_date");
    }

    @Override
    public List<Order> getArchivedOrdersCompletionDateSorted() {
        return getAllOrdersSortedByCriteria("completion_date");
    }

    @Override
    public List<Order> getArchivedOrdersPriceSorted() {
        return getAllOrdersSortedByCriteria("price");
    }

    @Override
    public Order getOrderByCraftsmen(long craftsmanId) {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);
            Join<Order, Craftsman> joinCraftsman = root.join("craftsman");
            Predicate predicate = builder.and(builder.equal(joinCraftsman.get("id"), craftsmanId),
                    builder.equal(root.get("order_status_id"), 3));
            criteriaQuery.where(predicate);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception exception) {
            logger.error("Can't get order by craftsman id", exception);
        }
        return null;
    }

    private List<Order> getAllOrdersSortedByCriteria(String criteria) {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);
            criteriaQuery
                    .select(root)
                    .orderBy(builder.asc(root.get(criteria)));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            logger.error("Can't get orders sorted by " + criteria, exception);
        }
        return Collections.EMPTY_LIST;
    }

    private List<Order> getOrdersArchivedSortedByCriteria(String criteria) {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);
            Predicate predicate = builder.and(builder.notEqual(root.get("order_status_id"), 1),
                    builder.notEqual(root.get("order_status_id"), 3));
            criteriaQuery.where(predicate);
            criteriaQuery
                    .select(root)
                    .orderBy(builder.asc(root.get(criteria)));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            logger.error("Can't get archived orders sorted by " + criteria, exception);
        }

        return Collections.EMPTY_LIST;
    }

    private List<Order> getOrdersInProgressSortedByCriteria(String criteria) {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = builder.createQuery(Order.class);
            Root<Order> root = criteriaQuery.from(Order.class);
            Predicate predicate = builder.equal(root.get("order_status_id"), 3);
            criteriaQuery.where(predicate);
            criteriaQuery
                    .select(root)
                    .orderBy(builder.asc(root.get(criteria)));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            logger.error("Can't get orders in progress sorted by " + criteria, exception);
        }
        return Collections.EMPTY_LIST;
    }
}