package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.UserBuilder;
import com.coderhouse.ecommerce.cache.CacheClient;
import com.coderhouse.ecommerce.exception.LoginErrorException;
import com.coderhouse.ecommerce.exception.UserAlreadyExistException;
import com.coderhouse.ecommerce.model.request.UserLogin;
import com.coderhouse.ecommerce.model.request.UserRegister;
import com.coderhouse.ecommerce.model.response.UserResponse;
import com.coderhouse.ecommerce.repository.UserRepository;
import com.coderhouse.ecommerce.security.JwtProvider;
import com.coderhouse.ecommerce.service.EmailService;
import com.coderhouse.ecommerce.service.UserService;
import com.coderhouse.ecommerce.util.CheckExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private CacheClient<String> cache;
    @Autowired
    private CheckExist checkExist;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private EmailService emailService;


    @Override
    public UserResponse register(UserRegister request) throws Exception {
        var userEmail = request.getEmail();
        if(checkExist.user(userEmail)) {
            throw new UserAlreadyExistException();
        }
        //se guarda en redis el usuario (email) con su token
        var token = cache.save(userEmail, jwtProvider.getJWTToken(userEmail));

        //encriptando la contraseña
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        var document = repository.save(UserBuilder.requestRegisterToDocument(request));
        emailService.sendNewUserEmail(document);
        return UserBuilder.documentToResponse(document, token);
    }

    @Override
    public UserResponse login(UserLogin request) throws Exception {
        var userEmail = request.getEmail();
        if(!checkExist.user(userEmail) ||
                !passwordEncoder.matches(request.getPassword(), getPasswordByEmail(userEmail))) {
            throw new LoginErrorException();
        }

        //si el usuario existe en redis devuelvo el token JWT
        if(cache.exist(userEmail)) {
            var tokenCache = cache.recover(userEmail, String.class);

            if(!jwtProvider.isExpired(tokenCache)) {
                return UserBuilder.emailToResponse(userEmail, tokenCache);
            }
        }
        //si el usuario no existe en redis (expiró caché) o el token expiró
        var token = cache.save(userEmail, jwtProvider.getJWTToken(userEmail));
        return UserBuilder.emailToResponse(userEmail, token);
    }

    private String getPasswordByEmail(String email) {
        return repository.findByEmail(email).getPassword();
    }
}
