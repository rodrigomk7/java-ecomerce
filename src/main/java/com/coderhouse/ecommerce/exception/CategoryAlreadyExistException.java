package com.coderhouse.ecommerce.exception;

public class CategoryAlreadyExistException extends ApiException {
    public CategoryAlreadyExistException(){
        super("Category already exist");
    }
}
