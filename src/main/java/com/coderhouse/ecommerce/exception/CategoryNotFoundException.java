package com.coderhouse.ecommerce.exception;

public class CategoryNotFoundException extends ApiException {
    public CategoryNotFoundException(){
        super("Category not found");
    }
}
