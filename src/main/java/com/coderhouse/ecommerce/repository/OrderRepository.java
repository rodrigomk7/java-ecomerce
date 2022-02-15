package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.document.OrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<OrderDocument, String> {
    OrderDocument findByOrderNumber(Long orderNumber);
}
