package com.coderhouse.ecommerce.exception;

public class CartNotFoundException extends ApiException {
    public CartNotFoundException(){
        super("Cart not found");
    }
}
