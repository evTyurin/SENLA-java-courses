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

    @Autowired
    private ICraftsmanDao craftsmanDao;

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
    public List<Craftsman> getSortedAlphabetically() {
        return craftsmanDao.getSortedAlphabetically();
    }

    @Override
    public List<Craftsman> getSortedByBusyness() {
        return craftsmanDao.getSortedByBusyness();
    }

    @Override
    public Craftsman getCraftsmanById(Long id) throws NotFoundException {
        return craftsmanDao.findById(id);
    }

    @Override
    public Craftsman createCraftsmen(String name, String surname) {
        Craftsman craftsman = new Craftsman();
        craftsman.setName(name);
        craftsman.setSurname(surname);
        return craftsman;
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
}
