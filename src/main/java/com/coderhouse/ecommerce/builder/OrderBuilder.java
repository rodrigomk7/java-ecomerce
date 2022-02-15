package com.coderhouse.ecommerce.builder;

import com.coderhouse.ecommerce.model.document.CartDocument;
import com.coderhouse.ecommerce.model.document.OrderDocument;
import com.coderhouse.ecommerce.model.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderBuilder {

    @Autowired
    private ItemBuilder itemBuilder;

    public OrderDocument cartDocumentToDocument(CartDocument document, Long orderRepoCount) {
        return OrderDocument.builder()
                .orderNumber(orderRepoCount + 1)
                .email(document.getEmail())
                .products(document.getProducts())
                .orderDate(LocalDateTime.now())
                .stateOfOrder("generated")
                .shippingAddress(document.getShippingAddress())
                .build();
    }

    public OrderResponse documentToResponse(OrderDocument document) {
        return OrderResponse.builder()
                .orderNumber(document.getOrderNumber())
                .products(itemBuilder.listToListResponse(document.getProducts()))
                .orderDate(document.getOrderDate())
                .email(document.getEmail())
                .stateOfOrder(document.getStateOfOrder())
                .shippingAddress(document.getShippingAddress())
                .build();

    }

    public List<OrderResponse> listDocumentsToListResponse(List<OrderDocument> documents) {
        var listResponse = new ArrayList<OrderResponse>();
        documents.forEach(document -> listResponse.add(documentToResponse(document)));
        return listResponse;
    }


}
