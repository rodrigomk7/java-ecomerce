package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.CartBuilder;
import com.coderhouse.ecommerce.builder.ItemBuilder;
import com.coderhouse.ecommerce.exception.*;
import com.coderhouse.ecommerce.model.request.CartItem;
import com.coderhouse.ecommerce.model.request.CartRequest;
import com.coderhouse.ecommerce.model.response.CartResponse;
import com.coderhouse.ecommerce.model.response.ItemResponse;
import com.coderhouse.ecommerce.repository.CartRepository;
import com.coderhouse.ecommerce.service.CartService;
import com.coderhouse.ecommerce.util.CheckExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    private CartRepository repository;
    @Autowired
    private CheckExist checkExist;
    @Autowired
    private CartBuilder cartBuilder;
    @Autowired
    private ItemBuilder itemBuilder;


    @Override
    public CartResponse create(CartRequest request) throws Exception {
        var email = request.getEmail();
        if(!checkExist.user(email)) {
            throw new UserNotFoundException();
        }

        if(checkExist.cart(email)) {
            throw new CartAlreadyExistException();
        }

        var document = repository.save(cartBuilder.requestToDocument(request));
        return cartBuilder.documentToResponse(document);
    }

    @Override
    public CartResponse getCart(String email) throws Exception {
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }
        return cartBuilder.documentToResponse(repository.findByEmail(email));
    }

    @Override
    public List<CartResponse> getAll() {
        return cartBuilder.listDocumentsToListResponse(repository.findAll());
    }

    @Override
    public CartResponse update(CartRequest request) throws Exception {
        var email = request.getEmail();
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }

        var document = cartBuilder.updateRequestToDocument(request, repository.findByEmail(email));
        return cartBuilder.documentToResponse(repository.save(document));
    }

    @Override
    public CartResponse delete(String email) throws Exception {
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }
        var document = repository.findByEmail(email);
        repository.delete(document);
        return cartBuilder.documentToResponse(document);
    }

    @Override
    public CartResponse addItem(CartItem request) throws Exception {
        var email = request.getEmail();
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }

        var product = request.getItem();
        var codeItem = product.getProductCode();
        if(!checkExist.product(codeItem)) {
            throw new ProductNotFoundException();
        }

        var document = repository.findByEmail(email);
        if(!document.getProducts().isEmpty() &&
                document.getProducts().stream().anyMatch(item -> item.getProductCode().equals(codeItem))) {
            throw new ProductAlreadyInCartException();
        }

        document.getProducts().add(product);
        return cartBuilder.documentToResponse(repository.save(document));
    }

    @Override
    public CartResponse updateItem(CartItem request) throws Exception {
        var email = request.getEmail();
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }

        var product = request.getItem();
        var codeItem = product.getProductCode();
        var document = repository.findByEmail(email);
        if(!checkExist.product(codeItem) || !document.getProducts().stream().anyMatch(item -> item.getProductCode().equals(codeItem))) {
            throw new ProductNotFoundException();
        }

        document.getProducts().stream().filter(item -> item.getProductCode().equals(codeItem))
                    .findFirst()
                    .get()
                    //si el item ya está en carrito se actualiza el valor de cantidad
                    .setQuantity(product.getQuantity());

        return cartBuilder.documentToResponse(repository.save(document));
    }

    @Override
    public List<ItemResponse> getAllItems(String email) throws Exception {
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }
        var items = repository.findByEmail(email).getProducts();
        return itemBuilder.listToListResponse(items);
    }

    @Override
    public CartResponse deleteItem(String email, String productCode) throws Exception {

        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }

        var document = repository.findByEmail(email);
        if(!checkExist.product(productCode) || !document.getProducts().stream().anyMatch(item -> item.getProductCode().equals(productCode))) {
            throw new ProductNotFoundException();
        }

        var documents = document.getProducts().stream().filter(item -> !item.getProductCode().equals(productCode));
        document.setProducts(documents.collect(Collectors.toList()));
        return cartBuilder.documentToResponse(repository.save(document));
    }
}
