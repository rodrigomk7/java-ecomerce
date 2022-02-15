package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.ProductBuilder;
import com.coderhouse.ecommerce.cache.CacheClient;
import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.exception.ProductAlreadyExistException;
import com.coderhouse.ecommerce.exception.ProductNotFoundException;
import com.coderhouse.ecommerce.model.document.ProductDocument;
import com.coderhouse.ecommerce.model.request.ProductRequest;
import com.coderhouse.ecommerce.model.request.ProductUpdateRequest;
import com.coderhouse.ecommerce.model.response.ProductResponse;
import com.coderhouse.ecommerce.repository.ProductRepository;
import com.coderhouse.ecommerce.service.ProductService;
import com.coderhouse.ecommerce.util.CheckExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CacheClient<ProductDocument> cache;
    @Autowired
    private CheckExist checkExist;


    @Override
    public ProductResponse create(ProductRequest request) throws Exception {
        if(checkExist.product(request.getCode())) {
            throw new ProductAlreadyExistException();
        }

        if(!checkExist.category(request.getCategoryCode())) {
            throw new CategoryNotFoundException();
        }

        var document = repository.save(ProductBuilder.requestToDocument(request));
        var documentCache = saveProductInCache(document);
        return ProductBuilder.documentToResponse(documentCache);
    }

    @Override
    public ProductResponse getByCode(String code) throws Exception {
        if(!checkExist.product(code)){
            throw new ProductNotFoundException();
        }
        //busco en cache
        if(cache.exist(code)){
            var documentCache = cache.recover(code, ProductDocument.class);
            return ProductBuilder.documentToResponse(documentCache);
        }
        var documentCache = saveProductInCache(repository.findByCode(code));
        return ProductBuilder.documentToResponse(documentCache);
    }

    @Override
    public List<ProductResponse> getByCategory(String categoryCode) throws Exception {
        if(!checkExist.category(categoryCode)) {
            throw new CategoryNotFoundException();
        }
        return ProductBuilder.listDocumentsToListResponse(repository.findByCategoryCode(categoryCode));
    }

    @Override
    public List<ProductResponse> getAll() {
        return ProductBuilder.listDocumentsToListResponse(repository.findAll());
    }

    @Override
    public ProductResponse update(ProductUpdateRequest request) throws Exception {
        var productCode = request.getCode();
        if(!checkExist.product(productCode)) {
            throw new ProductNotFoundException();
        }
        //se quiere actualizar categoría que no existe
        if(request.getCategoryCode() != null) {
            if(!checkExist.category(request.getCategoryCode())) {
                throw new CategoryNotFoundException();
            }
        }

        var document = ProductBuilder.updateRequestToDocument(request, repository.findByCode(productCode));
        var documentCache = saveProductInCache(document);
        var documentSaved = repository.save(documentCache);
        return ProductBuilder.documentToResponse(documentSaved);
    }

    @Override
    public ProductResponse delete(String code) throws Exception {
        if(!checkExist.product(code)){
            throw new ProductNotFoundException();
        }
        var document = repository.findByCode(code);
        repository.delete(document);
        cache.delete(code);
        return ProductBuilder.documentToResponse(document);
    }

    private ProductDocument saveProductInCache(ProductDocument product) throws Exception {
        return cache.save(product.getCode(), product);
    }
}
