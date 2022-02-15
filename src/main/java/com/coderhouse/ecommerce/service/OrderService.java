package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.model.request.OrderRequest;
import com.coderhouse.ecommerce.model.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse create(OrderRequest request) throws Exception;
    OrderResponse getOrder(Long orderNumber) throws Exception;
    List<OrderResponse> getAll();
}
