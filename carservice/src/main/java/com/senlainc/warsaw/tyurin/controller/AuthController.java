package com.senlainc.warsaw.tyurin.controller;

import com.senlainc.warsaw.tyurin.dto.JwtDto;
import com.senlainc.warsaw.tyurin.dto.UserDto;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.exception.UserExistException;
import com.senlainc.warsaw.tyurin.service.IAuthService;
import com.senlainc.warsaw.tyurin.util.mapper.UserMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final UserMapper userMapper;
    private IAuthService authService;

    public AuthController(UserMapper userMapper,
                          IAuthService authService) {
        this.userMapper = userMapper;
        this.authService = authService;
    }

    @PostMapping("login")
    public JwtDto login(@RequestBody UserDto userDto){
        return new JwtDto(authService.login(userMapper.mapToEntity(userDto)));
    }

    @PostMapping("register")
    public void register(@RequestBody UserDto userDto) throws NotFoundException, UserExistException {
        authService.register(userMapper.mapToEntity(userDto));
    }
}
