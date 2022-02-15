package com.coderhouse.ecommerce.exception;

public class OrderNotFoundException extends ApiException {
    public OrderNotFoundException(){
        super("Order not found");
    }
}
