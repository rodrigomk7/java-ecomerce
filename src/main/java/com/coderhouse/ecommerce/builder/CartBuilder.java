package com.coderhouse.ecommerce.builder;

import com.coderhouse.ecommerce.model.document.CartDocument;
import com.coderhouse.ecommerce.model.request.CartRequest;
import com.coderhouse.ecommerce.model.response.CartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class CartBuilder {

    @Autowired
    private ItemBuilder itemBuilder;

    public CartDocument requestToDocument(CartRequest request) {
        return CartDocument.builder()
                .email(request.getEmail())
                .modificationDate(LocalDateTime.now())
                .shippingAddress(request.getShippingAddress())
                .build();
    }

    public CartDocument updateRequestToDocument(CartRequest request, CartDocument document) {
        return CartDocument.builder()
                .email(request.getEmail())
                .modificationDate(LocalDateTime.now())
                .shippingAddress(request.getShippingAddress())
                .products(document.getProducts())
                .build();
    }

    public CartResponse documentToResponse(CartDocument document) {
        return CartResponse.builder()
                .email(document.getEmail())
                .products(itemBuilder.listToListResponse(document.getProducts()))
                .modificationDate(document.getModificationDate())
                .shippingAddress(document.getShippingAddress())
                .build();
    }

    public List<CartResponse> listDocumentsToListResponse(List<CartDocument> documents) {
        var listResponse = new ArrayList<CartResponse>();
        documents.forEach(document -> listResponse.add(documentToResponse(document)));
        return listResponse;
    }
}
