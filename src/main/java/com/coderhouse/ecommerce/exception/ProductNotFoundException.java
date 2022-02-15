package com.coderhouse.ecommerce.exception;

public class ProductNotFoundException extends ApiException {
    public ProductNotFoundException(){
        super("Product not found");
    }
}
