package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.dao.IUserDao;
import com.senlainc.warsaw.tyurin.entity.Role;
import com.senlainc.warsaw.tyurin.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class}, loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = "classpath:test-application.properties")
class CustomUserDetailsServiceUnitTest {

    @Autowired
    IUserDao userDao;
    @Autowired
    UserDetailsService userDetailsService;

    @Test
    void find_findUserByUsername_returnedUser() {
        UserEntity testUserEntity = new UserEntity();
        testUserEntity.setUsername("username");
        testUserEntity.setPassword("password");
        Role role = new Role();
        role.setId(1);
        role.setName("USER");
        testUserEntity.setRoles(new ArrayList<>(Collections.singletonList(role)));

        when(userDao.findUserByUsername("username")).thenReturn(testUserEntity);

        UserEntity returnedUserEntity = userDao.findUserByUsername("username");

        assertEquals(returnedUserEntity, testUserEntity);

        verify(userDao).findUserByUsername("username");
    }
}
