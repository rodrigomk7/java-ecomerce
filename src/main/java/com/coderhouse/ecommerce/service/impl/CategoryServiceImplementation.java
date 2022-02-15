package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.CategoryBuilder;
import com.coderhouse.ecommerce.cache.CacheClient;
import com.coderhouse.ecommerce.exception.CategoryAlreadyExistException;
import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.model.document.CategoryDocument;
import com.coderhouse.ecommerce.model.request.CategoryRequest;
import com.coderhouse.ecommerce.model.response.CategoryResponse;
import com.coderhouse.ecommerce.repository.CategoryRepository;
import com.coderhouse.ecommerce.service.CategoryService;
import com.coderhouse.ecommerce.util.CheckExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CacheClient<CategoryDocument> cache;
    @Autowired
    private CheckExist checkExist;


    @Override
    public CategoryResponse create(CategoryRequest request) throws Exception {
        if(checkExist.category(request.getCode())) {
            throw new CategoryAlreadyExistException();
        }
        var document = repository.save(CategoryBuilder.requestToDocument(request));
        var documentCache = saveCategoryInCache(document);
        return CategoryBuilder.documentToResponse(documentCache);
    }

    @Override
    public CategoryResponse getByCode(String code) throws Exception {
        if(!checkExist.category(code)) {
            throw new CategoryNotFoundException();
        }

        if(cache.exist(code)) {
            var documentCache = cache.recover(code, CategoryDocument.class);
            return CategoryBuilder.documentToResponse(documentCache);
        }
        var documentCache = saveCategoryInCache(repository.findByCode(code));
        return CategoryBuilder.documentToResponse(documentCache);
    }

    @Override
    public List<CategoryResponse> getAll() {
        return CategoryBuilder.listDocumentsToListResponse(repository.findAll());
    }

    @Override
    public CategoryResponse update(CategoryRequest request) throws Exception {
        if(!checkExist.category(request.getCode())) {
            throw new CategoryNotFoundException();
        }

        var document = CategoryBuilder.requestToDocument(request);
        //setteo el id de mongodb
        document.setId(repository.findByCode(request.getCode()).getId());
        var documentCache = saveCategoryInCache(document);
        var documentSaved = repository.save(documentCache);
        return CategoryBuilder.documentToResponse(documentSaved);
    }

    @Override
    public CategoryResponse delete(String code) throws Exception {
        if(!checkExist.category(code)) {
            throw new CategoryNotFoundException();
        }
        var document = repository.findByCode(code);
        repository.delete(document);
        cache.delete(code);
        return CategoryBuilder.documentToResponse(document);
    }

    private CategoryDocument saveCategoryInCache(CategoryDocument category) throws Exception {
        return cache.save(category.getCode(), category);
    }
}
