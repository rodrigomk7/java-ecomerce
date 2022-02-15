package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.document.CartDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<CartDocument, String> {
    CartDocument findByEmail(String email);
    boolean existsByEmail(String email);
}
