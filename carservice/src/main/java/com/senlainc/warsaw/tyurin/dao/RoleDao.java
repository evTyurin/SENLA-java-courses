package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Repository
public class RoleDao extends AbstractGenericDao<Role> implements IRoleDao {

    private final static Logger logger = Logger.getLogger(Role.class);

    @Override
    public Role findByName(String name) {
        try (Session session = hibernateUtil.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
            Root<Role> root = criteriaQuery.from(Role.class);
            Predicate predicate = builder.equal(root.get("name"), name);
            criteriaQuery.where(predicate);
            criteriaQuery
                    .select(root);
            return session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception exception) {
            logger.error("Can't find role " + name);
        }
        return null;
    }
}
