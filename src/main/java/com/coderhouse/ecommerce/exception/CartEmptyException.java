package com.coderhouse.ecommerce.exception;

public class CartEmptyException extends ApiException {
    public CartEmptyException(){
        super("Cart is empty. Add item before creating order.");
    }
}
