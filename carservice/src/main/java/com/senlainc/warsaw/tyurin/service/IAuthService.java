package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.entity.UserEntity;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.exception.UserExistException;

public interface IAuthService {

    void register(UserEntity user) throws NotFoundException, UserExistException;

    String login(UserEntity user);
}
