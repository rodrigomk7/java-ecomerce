package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserDocument, String> {
    UserDocument findByEmail(String email);
    boolean existsByEmail(String email);
}
