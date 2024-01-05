package com.senlainc.warsaw.tyurin;

import com.senlainc.warsaw.tyurin.dao.*;
import com.senlainc.warsaw.tyurin.security.JwtGenerator;
import com.senlainc.warsaw.tyurin.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public ICraftsmanDao mockCraftsmanDao() {
        return mock(ICraftsmanDao.class);
    }

    @Bean
    public IOrderDao mockOrderDao() {
        return mock(IOrderDao.class);
    }

    @Bean
    public IUserDao mockUserDao() {
        return mock(IUserDao.class);
    }

    @Bean
    public IGaragePlaceDao mockGaragePlaceDao() {
        return mock(IGaragePlaceDao.class);
    }

    @Bean
    public IRoleDao mockRoleDao() {
        return mock(IRoleDao.class);
    }

    @Bean
    public AuthenticationManager mockAuthenticationManager() {
        return mock(AuthenticationManager.class);
    }

    @Bean
    public JwtGenerator mockJwtGenerator() {
        return mock(JwtGenerator.class);
    }

    @Bean
    public PasswordEncoder mockPasswordEncoder() {
        return mock(PasswordEncoder.class);
    }

    @Bean
    public ICraftsmanService mockCraftsmanService() {
        return new CraftsmanService(mockCraftsmanDao());
    }

    @Bean
    public IGaragePlaceService mockGaragePlaceService() {
        return new GaragePlaceService(mockGaragePlaceDao());
    }

    @Bean
    public IOrderService mockOrderService() {
        return new OrderService(mockOrderDao());
    }

    @Bean
    public AuthService mockAuthService() {
        return new AuthService(mockUserDao(),
                mockRoleDao(),
                mockAuthenticationManager(),
                mockJwtGenerator(),
                mockPasswordEncoder());
    }

    @Bean
    public UserDetailsService mockUserDetailsService() {
        return new CustomUserDetailsService(mockUserDao());
    }
}
