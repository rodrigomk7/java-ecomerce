package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.document.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductDocument, String> {
    ProductDocument findByCode(String code);
    List<ProductDocument> findByCategoryCode(String categoryCode);
    boolean existsByCode(String code);
}
