package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.request.CategoryRequest;
import com.coderhouse.ecommerce.model.response.CategoryResponse;
import com.coderhouse.ecommerce.service.CategoryService;
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
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Operation(summary = "Obtener una categoría por código", description = "Permite obtener la información " +
            "de una categoría a partir de su código")
    @ApiResponse(responseCode = "200", description = "Se obtiene una respuesta con el código de la categoría, " +
            "su nombre y la fecha de su última modificación.")
    @GetMapping("/{categoryCode}")
    public CategoryResponse getCategoryByCode(@PathVariable String categoryCode) throws Exception {
        log.info("GET REQUEST ... getCategoryByCode | Fecha de ejecución: " + LocalDateTime.now());
        return service.getByCode(categoryCode);
    }

    @Operation(summary = "Obtener todas las categorías", description = "Permite obtener una lista con todas las " +
            "categorías disponibles.")
    @ApiResponse(responseCode = "200", description = "Lista de objetos, cada uno representa una categoría disponible, " +
            "con su código, nombre y fecha de última modificación.")
    @GetMapping("/all")
    public List<CategoryResponse> getAllCategories() {
        log.info("GET REQUEST ... getAllCategories | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAll();
    }

    @Operation(summary = "Crear una nueva categoría", description = "Permite crear una nueva categoría. " +
            "Se requiere de un código único que comience con 'C' y un nombre de la categoría. Por ejemplo, para " +
            "la venta de productos alimenticios, un posible nombre de categoría podría ser: Lácteos, Carnes, Harinas.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta la categoría creada, con su " +
            "fecha de creación.")
    @PostMapping("")
    public CategoryResponse createCategory(@Validated @RequestBody CategoryRequest request) throws Exception {
        log.info("POST REQUEST ... createCategory | Fecha de ejecución: " + LocalDateTime.now());
        return service.create(request);
    }

    @Operation(summary = "Modificar una categoría", description = "Permite modificar el nombre de una categoría. " +
            "El objeto de categoría enviado para la solicitud tiene que contener un código de categoría existente.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta la categoría modificada, con su " +
            "fecha de última modificación.")
    @PutMapping("")
    public CategoryResponse updateCategory(@Validated @RequestBody() CategoryRequest request) throws Exception {
        log.info("UPDATE REQUEST ... updateCategory | Fecha de ejecución: " + LocalDateTime.now());
        return service.update(request);
    }

    @Operation(summary = "Eliminar una categoría", description = "Permite eliminar una categoría. " +
            "Como parámetro se debe enviar un código de categoría existente.")
    @ApiResponse(responseCode = "200", description = "Se obtiene como respuesta la categoría eliminada.")
    @DeleteMapping("/{categoryCode}")
    public CategoryResponse deleteCategory(@PathVariable String categoryCode) throws Exception {
        log.info("DELETE REQUEST ... deleteCategory | Fecha de ejecución: " + LocalDateTime.now());
        return service.delete(categoryCode);
    }

}
