package com.senlainc.warsaw.tyurin.dao;

import com.senlainc.warsaw.tyurin.entity.UserEntity;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;

public interface IUserDao {

    void create(UserEntity userEntity) throws NotFoundException;

    UserEntity findById(long id) throws NotFoundException;

    void update(UserEntity userEntity);

    void delete(UserEntity userEntity);

    UserEntity findUserByUsername(String login);
}
