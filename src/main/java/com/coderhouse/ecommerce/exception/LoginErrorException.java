package com.coderhouse.ecommerce.exception;

public class LoginErrorException extends ApiException {
    public LoginErrorException(){
        super("Email or password incorrect");
    }
}
