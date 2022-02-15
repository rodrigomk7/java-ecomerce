package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.document.CategoryDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<CategoryDocument, String> {
    CategoryDocument findByCode(String code);
    boolean existsByCode(String code);
}
