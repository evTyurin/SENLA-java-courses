package com.senlainc.warsaw.tyurin.service;

import com.senlainc.warsaw.tyurin.security.JwtGenerator;
import com.senlainc.warsaw.tyurin.dao.IRoleDao;
import com.senlainc.warsaw.tyurin.dao.IUserDao;
import com.senlainc.warsaw.tyurin.entity.Role;
import com.senlainc.warsaw.tyurin.entity.UserEntity;
import com.senlainc.warsaw.tyurin.exception.NotFoundException;
import com.senlainc.warsaw.tyurin.exception.UserExistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService implements IAuthService {

    private final static Logger logger = Logger.getLogger(AuthService.class);

    private IUserDao userDao;
    private IRoleDao roleDao;
    private AuthenticationManager authenticationManager;
    private JwtGenerator jwtGenerator;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(IUserDao userDao,
                       IRoleDao roleDao,
                       AuthenticationManager authenticationManager,
                       JwtGenerator jwtGenerator,
                       PasswordEncoder passwordEncoder) {
        this.roleDao = roleDao;
        this.userDao = userDao;
        this.jwtGenerator = jwtGenerator;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserEntity user) throws NotFoundException, UserExistException {
        if(userDao.findUserByUsername(user.getUsername()) != null) {
            throw new UserExistException(user.getUsername());
        }
        Role roles = roleDao.findByName("USER");
        user.setRoles(Collections.singletonList(roles));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.create(user);
    }

    @Override
    public String login(UserEntity user) {
        Authentication authentication = null;
        try  {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException ex) {
            logger.error("Incorrect username or password");
        }
        return jwtGenerator.generateToken(authentication);
    }
}
