package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.annotation.DependencyClass;
import com.senlainc.warsaw.tyurin.annotation.DependencyInitMethod;
import com.senlainc.warsaw.tyurin.entity.GaragePlace;
import com.senlainc.warsaw.tyurin.entity.Order;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import javax.persistence.criteria.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@DependencyClass
public class GaragePlaceDao extends AbstractGenericDao<GaragePlace> implements IGaragePlaceDao {

    private final static Logger logger = Logger.getLogger(GaragePlaceDao.class);
    private static GaragePlaceDao INSTANCE;

    public static GaragePlaceDao getInstance() {
        return INSTANCE;
    }

    @DependencyInitMethod
    public void setInstance() {
        INSTANCE = this;
    }

    @Override
    public List<GaragePlace> getAvailableGaragePlaces() {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<GaragePlace> criteriaQuery = builder.createQuery(GaragePlace.class);
            Root<GaragePlace> root = criteriaQuery.from(GaragePlace.class);
            Join<GaragePlace, Order> joinOrder = root.join("orders");
            Predicate predicate = builder.equal(joinOrder.get("order_status_id"), 3);
            criteriaQuery.where(predicate);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            logger.error("Can't available garage places", exception);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public long getAvailablePlacesAmount(LocalDateTime localDateTime) {
        try (Session session = hibernateUtil.openSession()) {
            NativeQuery<Long> query = session
                    .createNativeQuery("select LEAST(count(order_craftsman.order_id), count(order_craftsman.craftsman_id)) as amount\n" +
                            "from orders join garage_place on garage_place.id=orders.garage_place_id \n" +
                            "join order_craftsman on order_craftsman.order_id=orders.id\n" +
                            "where (completion_date < ?1 or start_date > ?2) and order_status_id!=4 and order_craftsman.craftsman_id not in (select distinct craftsman_id\n" +
                            "from orders join garage_place on garage_place.id=orders.garage_place_id \n" +
                            "join order_craftsman on order_craftsman.order_id=orders.id\n" +
                            "where start_date < ?3 and completion_date > ?4 and order_status_id!=4);")
                    .setParameter(1, Timestamp.valueOf(localDateTime))
                    .setParameter(2, Timestamp.valueOf(localDateTime))
                    .setParameter(3, Timestamp.valueOf(localDateTime))
                    .setParameter(4, Timestamp.valueOf(localDateTime));
            return query.getFirstResult();
        } catch (Exception exception) {
            logger.error("Can't available garage places amount", exception);
        }
        return 0;
    }
}
