package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.request.ProductRequest;
import com.coderhouse.ecommerce.model.request.ProductUpdateRequest;
import com.coderhouse.ecommerce.model.response.ProductResponse;
import com.coderhouse.ecommerce.service.ProductService;
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
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @Operation(summary = "Obtener un producto por su código", description = "Permite obtener la información " +
            "de un producto a partir de su código")
    @ApiResponse(responseCode = "200", description = "Se obtiene una respuesta con el código del producto, " +
            "su nombre, la fecha de su última modificación y el código de su categoría.")
    @GetMapping("/{productCode}")
    public ProductResponse getProductByCode(@PathVariable String productCode) throws Exception {
        log.info("GET REQUEST ... getProductByCode | Fecha de ejecución: " + LocalDateTime.now());
        return service.getByCode(productCode);
    }

    @Operation(summary = "Obtener todos los productos", description = "Permite obtener una lista con todos los " +
            "productos disponibles.")
    @ApiResponse(responseCode = "200", description = "Lista de objetos, cada uno representa un producto disponible, " +
            "con su código, nombre, fecha de última modificación y código de su categoría.")
    @GetMapping("/all")
    public List<ProductResponse> getAllProducts() {
        log.info("GET REQUEST ... getAllProducts | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAll();
    }

    @Operation(summary = "Obtener todos los productos de una categoría", description = "Permite obtener la información " +
            "de todos los productos que tengan la misma categoría. Se requiere de un código único que comience con 'C'. ")
    @ApiResponse(responseCode = "200", description = "Se obtiene una lista de objetos de producto, cada uno representa " +
            "un producto disponible, con su código, nombre, fecha de última modificación y código de categoría.")
    @GetMapping("/category/{categoryCode}")
    public List<ProductResponse> getProductsByCategory(@PathVariable String categoryCode) throws Exception {
        log.info("GET REQUEST ... getProductsByCategory | Fecha de ejecución: " + LocalDateTime.now());
        return service.getByCategory(categoryCode);
    }

    @Operation(summary = "Crear un nuevo producto", description = "Permite crear un nuevo producto. " +
            "Se requiere de un código único que comience con 'P', un nombre para el producto, su precio y su categoría." +
            "También se puede enviar una descripción.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta el producto creado, con su " +
            "fecha de creación.")
    @PostMapping("")
    public ProductResponse createProduct(@Validated @RequestBody ProductRequest request) throws Exception {
        log.info("POST REQUEST ... createProduct | Fecha de ejecución: " + LocalDateTime.now());
        return service.create(request);
    }

    @Operation(summary = "Modificar un producto", description = "Permite modificar el nombre del producto, su " +
            "descripción, su precio y/o su categoría. " +
            "El objeto enviado para la solicitud tiene que contener un código de producto existente.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta el producto modificado, según los" +
            "parámetros en el RequestBody. Incluye  la fecha de última modificación.")
    @PutMapping("")
    public ProductResponse updateProduct(@Validated @RequestBody ProductUpdateRequest request) throws Exception {
        log.info("UPDATE REQUEST ... updateProduct | Fecha de ejecución: " + LocalDateTime.now());
        return service.update(request);
    }

    @Operation(summary = "Eliminar un producto", description = "Permite eliminar un producto. " +
            "Como parámetro se debe enviar un código de producto existente.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta el producto eliminado.")
    @DeleteMapping("/{productCode}")
    public ProductResponse deleteProduct(@PathVariable String productCode) throws Exception {
        log.info("DELETE REQUEST ... deleteProduct | Fecha de ejecución: " + LocalDateTime.now());
        return service.delete(productCode);
    }

}
