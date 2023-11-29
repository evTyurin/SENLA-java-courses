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
public class CraftsmanDao extends AbstractGenericDao<Craftsman> implements ICraftsmanDao {

    private final static Logger logger = Logger.getLogger(CraftsmanDao.class);

    @Override
    public List<Craftsman> getCraftsmenByOrder(long id) {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Craftsman> criteriaQuery = builder.createQuery(Craftsman.class);
            Root<Craftsman> root = criteriaQuery.from(Craftsman.class);
            Join<Craftsman, Order> joinOrder = root.join("orders");
            Predicate predicate = builder.equal(joinOrder.get("id"), id);
            criteriaQuery.where(predicate);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            logger.error("Can't get craftsman by order id", exception);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Craftsman> getSortedAlphabetically() {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Craftsman> criteria = builder.createQuery(Craftsman.class);
            Root<Craftsman> root = criteria.from(Craftsman.class);
            criteria
                    .select(root)
                    .orderBy(builder.asc(root.get("surname")),
                            builder.asc(root.get("name")));
            return session.createQuery(criteria).getResultList();
        } catch (Exception exception) {
            logger.error("Can't get craftsmen sorted alphabetically", exception);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public List<Craftsman> getSortedByBusyness() {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Craftsman> criteriaQuery = builder.createQuery(Craftsman.class);
            Root<Craftsman> root = criteriaQuery.from(Craftsman.class);
            Join<Craftsman, Order> joinOrder = root.join("orders");
            Predicate predicate = builder.equal(joinOrder.get("order_status_id"), 4);
            criteriaQuery.where(predicate);
            criteriaQuery.groupBy(root.get("id"));
            criteriaQuery
                    .select(root)
                    .orderBy(builder.asc(builder.count(root)));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            logger.error("Can't get craftsmen sorted by business", exception);
        }
        return Collections.EMPTY_LIST;
    }
}

