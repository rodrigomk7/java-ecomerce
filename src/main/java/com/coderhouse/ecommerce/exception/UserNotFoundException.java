package com.coderhouse.ecommerce.exception;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(){
        super("User not found");
    }
}
