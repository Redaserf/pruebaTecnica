package com.example.pruebatecnica.services;

import com.example.pruebatecnica.exceptions.UniqueException;
import com.example.pruebatecnica.models.Rol;
import com.example.pruebatecnica.models.Usuario;
import com.example.pruebatecnica.repositories.UsuarioRepository;
import com.example.pruebatecnica.requests.LoginRequest;
import com.example.pruebatecnica.requests.RegisterRequest;
import com.example.pruebatecnica.responses.AuthResponse;
import com.example.pruebatecnica.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails user = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("No se encontro el usuario con el email: " + request.getEmail()));

        String token = jwtService.getToken(user);

        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse registro(RegisterRequest request) throws UniqueException {

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new UniqueException("El email no es valido");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .fechaRegistro(LocalDateTime.now())
                .rol(Rol.USUARIO)
                .build();

        usuarioRepository.save(usuario);

        return AuthResponse.builder().token(jwtService.getToken(usuario)).build();
    }

}
