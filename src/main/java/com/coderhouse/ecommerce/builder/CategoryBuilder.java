package com.coderhouse.ecommerce.builder;

import com.coderhouse.ecommerce.model.document.CategoryDocument;
import com.coderhouse.ecommerce.model.request.CategoryRequest;
import com.coderhouse.ecommerce.model.response.CategoryResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {

    public static CategoryDocument requestToDocument(CategoryRequest request) {
        return CategoryDocument.builder()
                .code(request.getCode())
                .name(request.getName())
                .modificationDate(LocalDateTime.now())
                .build();
    }

    public static CategoryResponse documentToResponse(CategoryDocument document) {
        return CategoryResponse.builder()
                .code(document.getCode())
                .name(document.getName())
                .modificationDate(document.getModificationDate())
                .build();
    }

    public static List<CategoryResponse> listDocumentsToListResponse(List<CategoryDocument> documents) {
        var listResponse = new ArrayList<CategoryResponse>();
        documents.forEach(document -> listResponse.add(documentToResponse(document)));
        return listResponse;
    }
}
