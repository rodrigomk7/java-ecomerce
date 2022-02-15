package com.coderhouse.ecommerce.exception;

public class UserAlreadyExistException extends ApiException {
    public UserAlreadyExistException(){
        super("User already exist");
    }
}
