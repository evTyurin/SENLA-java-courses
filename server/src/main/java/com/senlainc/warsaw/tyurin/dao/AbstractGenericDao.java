package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

@Repository
public abstract class AbstractGenericDao<T> implements IGenericDao<T>{

    private final static Logger logger = Logger.getLogger(AbstractGenericDao.class);
    private final Class<T> entityClass;
    @Autowired
    protected HibernateUtil hibernateUtil;

    public AbstractGenericDao() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public T findById(long id) {
        return (T) hibernateUtil.openSession().get(this.entityClass, id);
    }

    @Override
    public void create(final T object) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.save(object);
            session.flush();
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't create entity from class " + entityClass.getName(), exception);
        }
    }

    @Override
    public void update(final T object) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.update(object);
            session.flush();
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't update entity from class " + entityClass.getName(), exception);
        }
    }

    @Override
    public void delete(final T object) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.openSession()) {
            transaction = session.beginTransaction();
            session.delete(object);
            session.flush();
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Can't delete entity from class " + entityClass.getName(), exception);
        }
    }

    @Override
    public List<T> getAll() {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
            Root<T> root = criteriaQuery.from(entityClass);
            criteriaQuery.select(root);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception exception) {
            logger.error("Can't get entities from class" + entityClass.getName(), exception);
        }
        return Collections.EMPTY_LIST;
    }
}
