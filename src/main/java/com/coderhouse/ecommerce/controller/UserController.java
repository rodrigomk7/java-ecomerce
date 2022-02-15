package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.request.UserLogin;
import com.coderhouse.ecommerce.model.request.UserRegister;
import com.coderhouse.ecommerce.model.response.UserResponse;
import com.coderhouse.ecommerce.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @Operation(summary = "Registrar un usuario", description = "Permite registrar a un usuario a partir de sus datos " +
            "como nombre, mail y teléfono. También se define una contraseña la cual es encriptada al momento " +
            "de ser almacenada en la base de datos." +
            "Al momento del registro se envía un mail de bienvenida al usuario y se pone en copia oculta al " +
            "mail configurado para eso (config-prop).")
    @ApiResponse(responseCode = "200", description = "Se obtiene una respuesta con el mail del nuevo usuario y el token " +
            "necesario para todas las solicitudes.")
    @PostMapping("")
    public UserResponse registerUser(@Validated @RequestBody UserRegister request) throws Exception {
        log.info("POST REQUEST ... registerUser | Fecha de ejecución: " + LocalDateTime.now());
        return service.register(request);
    }
    @Operation(summary = "Login de usuario", description = "Una vez que el usuario se encuentra registrado puede " +
            "solicitar su token con sus credenciales: email y contraseña.")
    @ApiResponse(responseCode = "200", description = "Se obtiene una respuesta con el mail del usuario y su token. " +
            "Para obtener el token se busca primero en caché (redis) y luego se verifica que el mismo no este vencido.")
    @PostMapping("/login")
    public UserResponse login(@Validated @RequestBody UserLogin request) throws Exception {
        log.info("POST REQUEST ... login | Fecha de ejecución: " + LocalDateTime.now());
        return service.login(request);
    }
}
