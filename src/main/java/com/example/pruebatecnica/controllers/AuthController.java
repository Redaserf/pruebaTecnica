package com.example.pruebatecnica.controllers;

import com.example.pruebatecnica.exceptions.UniqueException;
import com.example.pruebatecnica.requests.LoginRequest;
import com.example.pruebatecnica.requests.RegisterRequest;
import com.example.pruebatecnica.responses.AuthResponse;
import com.example.pruebatecnica.services.AuthService;
import com.example.pruebatecnica.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.AuthenticationException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) throws AuthenticationException {

        return ResponseEntity.ok(authService.login(request));

    }

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registro(@RequestBody @Valid RegisterRequest request) throws AuthenticationException, UniqueException {

        return ResponseEntity.ok(authService.registro(request));

    }
}
