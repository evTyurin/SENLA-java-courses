package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.dao.ICraftsmanDao;
import com.senlainc.warsaw.tyurin.entity.Craftsman;
import com.senlainc.warsaw.tyurin.exception.ExpectationFailedException;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CraftsmanService implements ICraftsmanService {

    private final static Logger logger = Logger.getLogger(CraftsmanService.class);

    private ICraftsmanDao craftsmanDao;

    @Autowired
    public CraftsmanService(ICraftsmanDao craftsmanDao) {
        this.craftsmanDao = craftsmanDao;
    }

    @Override
    public void addCraftsman(Craftsman craftsman) {
        craftsmanDao.create(craftsman);
    }

    @Override
    public void removeCraftsmanById(long id) throws NotFoundException {
        craftsmanDao.delete(craftsmanDao.findById(id));
    }

    @Override
    public List<Craftsman> getCraftsmenByOrder(long id) {
        return craftsmanDao.getCraftsmenByOrder(id);
    }

    @Override
    public Craftsman getCraftsmanById(Long id) throws NotFoundException {
        return craftsmanDao.findById(id);
    }

    @Override
    public List<Craftsman> getCraftsmen() {
        return craftsmanDao.getAll();
    }

    @Override
    public List<Craftsman> getSortedByCriteria(String criteria) throws ExpectationFailedException {
        if(criteria.equals("business")) {
            return getSortedByBusyness();
        } else if (criteria.equals("alphabetically")) {
            return getSortedAlphabetically();
        }
        throw new ExpectationFailedException(criteria);
    }

    private List<Craftsman> getSortedAlphabetically() {
        return craftsmanDao.getSortedAlphabetically();
    }

    private List<Craftsman> getSortedByBusyness() {
        return craftsmanDao.getSortedByBusyness();
    }
}
