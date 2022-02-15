package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.request.CartItem;
import com.coderhouse.ecommerce.model.request.CartRequest;
import com.coderhouse.ecommerce.model.response.CartResponse;
import com.coderhouse.ecommerce.model.response.ItemResponse;
import com.coderhouse.ecommerce.service.CartService;
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
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService service;

    @Operation(summary = "Crear un nuevo carrito de compras", description = "Permite crear un nuevo carrito de compras. " +
            "Se requiere un email de un usuario que se encuentre registrado y una dirección de entrega. " +
            "Un usuario puede tener un único carrito actual, pero más de una orden de compra. Una vez " +
            "que el usuario confirma su orden, su carro de compras se vacía.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta el carro creado, con su " +
            "fecha de creación.")
    @PostMapping("")
    public CartResponse createCart(@Validated @RequestBody CartRequest request) throws Exception {
        log.info("POST REQUEST ... createCart | Fecha de ejecución: " + LocalDateTime.now());
        return service.create(request);
    }

    @Operation(summary = "Obtener un carrito de compras por usuario", description = "Permite obtener toda la información " +
            "del carro de compras de un usuario a partir de su email.")
    @ApiResponse(responseCode = "200", description = "Se obtiene el carro de compras correspondiente.")
    @GetMapping("/{email}")
    public CartResponse getCartByEmail(@PathVariable String email) throws Exception {
        log.info("GET REQUEST ... getCartByEmail | Fecha de ejecución: " + LocalDateTime.now());
        return service.getCart(email);
    }

    @Operation(summary = "Obtener todos los carros de compras", description = "Permite obtener una lista con los carritos " +
            "de compras disponibles generados por los distintos usuarios.")
    @ApiResponse(responseCode = "200", description = "Lista de objetos, cada uno representa un carro de compra.")
    @GetMapping("/all")
    public List<CartResponse> getAll() {
        log.info("GET REQUEST ... getAll | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAll();
    }

    @Operation(summary = "Actualizar los datos del carrito", description = "Permite modificar la dirección de entrega " +
            "del carrito de compras correspondiente. El email enviado debe ser de un carro existente.")
    @ApiResponse(responseCode = "200", description = "Se obtiene el carro de compras actualizado.")
    @PutMapping("")
    public CartResponse updateCart(@Validated @RequestBody CartRequest request) throws Exception {
        log.info("PUT REQUEST ... updateCart | Fecha de ejecución: " + LocalDateTime.now());
        return service.update(request);
    }


    @Operation(summary = "Eliminar un carrito", description = "Permite eliminar un carro a partir del" +
            "email de un usuario registrado que tenga un carrito.")
    @ApiResponse(responseCode = "200", description = "Se obtiene el carro de compras eliminado.")
    @DeleteMapping("/{email}")
    public CartResponse deleteCart(@PathVariable String email) throws Exception {
        log.info("DELETE REQUEST ... deleteCart | Fecha de ejecución: " + LocalDateTime.now());
        return service.delete(email);
    }

    //Items
    @Operation(summary = "Agregar un producto al carro de compras", description = "Permite añadir un producto al carro. " +
            "Para identificar al carrito deseado se utiliza el email del usuario. " +
            "También se debe enviar un objeto Item, el cual contiene el código de un producto existente y " +
            "la cantidad de ese producto que se quiere agregar al carro.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta el carro de compras actualizado con" +
            "sus nuevos productos.")
    @PostMapping("/item")
    public CartResponse addItem(@RequestBody @Validated CartItem request) throws Exception{
        log.info("POST REQUEST ... addItem | Fecha de ejecución: " + LocalDateTime.now());
        return service.addItem(request);
    }

    @Operation(summary = "Modificar un producto del carro de compras", description = "Permite modificar la cantidad de . " +
            "un producto que se encuentre en el carrito. El valor de cantidad enviado sobreescribe al valor actual. " +
            "El código del producto enviado tiene que existir dentro del carro de compras.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta el carro de compras actualizado.")
    @PutMapping("/item")
    public CartResponse updateItem(@Validated @RequestBody CartItem request) throws Exception {
        log.info("PUT REQUEST ... updateItem | Fecha de ejecución: " + LocalDateTime.now());
        return service.updateItem(request);
    }

    @Operation(summary = "Eliminar un producto del carro de compras", description = "Permite eliminar un producto " +
            "existente en el carrito de compras. Se debe enviar el email asociado al carro de compras y el" +
            "código del producto a eliminar.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta el carro de compras actualizado.")
    @DeleteMapping("/item/{email}/{productCode}")
    public CartResponse deleteItem(@PathVariable String email, @PathVariable String productCode) throws Exception {
        log.info("DELETE REQUEST ... deleteItem | Fecha de ejecución: " + LocalDateTime.now());
        return service.deleteItem(email, productCode);
    }

    @Operation(summary = "Obtener todos los productos de un carro", description = "Permite obtener una lista con los " +
            "productos del carro de compras.")
    @ApiResponse(responseCode = "200", description = "Lista de objetos, cada uno representa un producto.")
    @GetMapping("/item/{email}")
    public List<ItemResponse> getItems(@PathVariable String email) throws Exception {
        log.info("GET REQUEST ... getItems | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAllItems(email);
    }

}
