package com.coderhouse.ecommerce.exception;

public class ProductAlreadyExistException extends ApiException {
    public ProductAlreadyExistException(){
        super("Product already exist");
    }
}
