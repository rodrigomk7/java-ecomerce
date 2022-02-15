package com.coderhouse.ecommerce.builder;

import com.coderhouse.ecommerce.model.document.UserDocument;
import com.coderhouse.ecommerce.model.request.UserRegister;
import com.coderhouse.ecommerce.model.response.UserResponse;

public class UserBuilder {

    public static UserDocument requestRegisterToDocument(UserRegister request) {
        return UserDocument.builder()
                .name(request.getName())
                .telephone(request.getTelephone())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public static UserResponse documentToResponse(UserDocument request, String token) {
        return UserResponse.builder()
                .email(request.getEmail())
                .token(token)
                .build();
    }

    public static UserResponse emailToResponse(String email, String token) {
        return UserResponse.builder()
                .email(email)
                .token(token)
                .build();
    }
}
