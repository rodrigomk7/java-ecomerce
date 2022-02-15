package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.request.OrderRequest;
import com.coderhouse.ecommerce.model.response.OrderResponse;
import com.coderhouse.ecommerce.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Operation(summary = "Finalizar compra", description = "Permite generar una orden de compra a partir de un carro " +
            "de compras. Esta solicitud ademas de confirmar la compra, vacía el carrito correspondiente. " +
            "Al momento de la compra se envía un mail al usuario de la compra con los detalles de la orden y se pone " +
            "en copia oculta al mail configurado para eso.")
    @ApiResponse(responseCode = "200", description = "Se obtiene la orden de compra generada.")
    @PostMapping("/buy")
    public OrderResponse createOrder(@Validated @RequestBody OrderRequest request) throws Exception {
        log.info("POST REQUEST ... createOrder | Fecha de ejecución: " + LocalDateTime.now());
        return service.create(request);
    }

    @Operation(summary = "Obtener todas las ordenes", description = "Permite obtener todas las ordenes. " +
            "Un mismo usuario puede tener más de una orden de compra.")
    @ApiResponse(responseCode = "200", description = "Se obtiene una lista con todas las ordenes generadas hasta el " +
            "momento.")
    @GetMapping("/all")
    public List<OrderResponse> getAllOrders() {
        log.info("GET REQUEST ... getAllOrders | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAll();
    }

    @Operation(summary = "Obtener la orden por número de orden", description = "Permite obtener la información " +
            "de una orden a partir de su número generado al momento de compra.")
    @ApiResponse(responseCode = "200", description = "Se obtiene una respuesta con la información de la orden" +
            " de compra.")
    @GetMapping("/{orderNumber}")
    public OrderResponse getOrder(@PathVariable Long orderNumber) throws Exception {
        log.info("GET REQUEST ... getOrder | Fecha de ejecución: " + LocalDateTime.now());
        return service.getOrder(orderNumber);
    }
}
