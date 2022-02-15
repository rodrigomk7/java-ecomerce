package com.coderhouse.ecommerce.util;

import com.coderhouse.ecommerce.repository.CartRepository;
import com.coderhouse.ecommerce.repository.CategoryRepository;
import com.coderhouse.ecommerce.repository.ProductRepository;
import com.coderhouse.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckExist {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    public boolean category(String code) {
        return categoryRepository.existsByCode(code);
    }

    public boolean product(String code) {
        return productRepository.existsByCode(code);
    }

    public boolean user(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean cart(String email) {
        return cartRepository.existsByEmail(email);
    }
}
