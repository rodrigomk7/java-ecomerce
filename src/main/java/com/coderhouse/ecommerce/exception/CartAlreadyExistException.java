package com.coderhouse.ecommerce.exception;

public class CartAlreadyExistException extends ApiException {
    public CartAlreadyExistException(){
        super("Cart already exist");
    }
}
