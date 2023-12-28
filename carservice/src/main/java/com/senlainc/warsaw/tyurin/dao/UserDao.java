package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class UserDao extends AbstractGenericDao<UserEntity> implements IUserDao{

    private final static Logger logger = Logger.getLogger(UserDao.class);

    @Override
    public UserEntity findUserByUsername(String login) {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserEntity> criteriaQuery = builder.createQuery(UserEntity.class);
            Root<UserEntity> root = criteriaQuery.from(UserEntity.class);
            Predicate predicate = builder.equal(root.get("login"), login);
            criteriaQuery.where(predicate);
            criteriaQuery
                    .select(root);
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception exception) {
            logger.error("Can't find user by login " + login);
        }
        return null;
    }
}
