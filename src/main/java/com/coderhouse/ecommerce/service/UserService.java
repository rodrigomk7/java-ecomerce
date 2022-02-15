package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.model.request.UserLogin;
import com.coderhouse.ecommerce.model.request.UserRegister;
import com.coderhouse.ecommerce.model.response.UserResponse;

public interface UserService {

    UserResponse register(UserRegister request) throws Exception;
    UserResponse login(UserLogin request) throws Exception;
}
