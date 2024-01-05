package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.dao.IRoleDao;
import com.senlainc.warsaw.tyurin.dao.IUserDao;
import com.senlainc.warsaw.tyurin.entity.Role;
import com.senlainc.warsaw.tyurin.entity.UserEntity;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.exception.UserExistException;
import com.senlainc.warsaw.tyurin.security.JwtGenerator;
import com.senlainc.warsaw.tyurin.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:test-application.properties")
class AuthServiceUnitTest {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IRoleDao roleDao;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthService authService;
    UserEntity testUserEntity;
    Role role;

    @BeforeEach
    void init() {
        testUserEntity = new UserEntity();
        testUserEntity.setUsername("username");
        testUserEntity.setPassword("password");

        role = new Role();
        role.setName("USER");
    }

    @Test
    void login_loginWithCorrectUsernamePassword_returnedToken() {

        testUserEntity.setRoles(new ArrayList<>(Collections.singletonList(role)));
        UsernamePasswordAuthenticationToken testUsernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                "username",
                "password", new ArrayList<>(Collections.singletonList(new SimpleGrantedAuthority("USER"))));
        Authentication testAuthentication = authenticationManager.authenticate(testUsernamePasswordAuthenticationToken);
        when(authenticationManager.authenticate(testUsernamePasswordAuthenticationToken)).thenReturn(testAuthentication);
        SecurityContextHolder.getContext().setAuthentication(testAuthentication);
        when(jwtGenerator.generateToken(testAuthentication)).thenReturn("token");
        String testToken = jwtGenerator.generateToken(testAuthentication);
        String returnedToken = authService.login(testUserEntity);
        assertEquals(returnedToken, testToken);
        verify(jwtGenerator, times(2)).generateToken(testAuthentication);
    }

    @Test
    void register_registerWithCorrectUsernamePassword_registeredSuccessfully() throws NotFoundException, UserExistException {
        when(userDao.findUserByUsername(testUserEntity.getUsername())).thenReturn(null);
        when(roleDao.findByName("USER")).thenReturn(role);
        testUserEntity.setRoles(Collections.singletonList(role));
        authService.register(testUserEntity);
        verify(userDao).findUserByUsername(testUserEntity.getUsername());
        verify(roleDao).findByName("USER");
        verify(passwordEncoder).encode("password");
    }

}
