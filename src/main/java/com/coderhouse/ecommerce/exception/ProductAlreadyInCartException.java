package com.coderhouse.ecommerce.exception;

public class ProductAlreadyInCartException extends ApiException{
    public ProductAlreadyInCartException(){
        super("Product is already in cart. Use put request for modification of quantity");
    }
}
