package com.senlainc.warsaw.tyurin.util.mapper;

import com.senlainc.warsaw.tyurin.dto.UserDto;
import com.senlainc.warsaw.tyurin.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserMapper() {}

    public UserEntity mapToEntity(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
