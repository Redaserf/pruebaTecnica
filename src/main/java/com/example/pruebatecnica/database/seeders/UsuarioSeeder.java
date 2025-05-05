package com.example.pruebatecnica.database.seeders;

import com.example.pruebatecnica.models.Rol;
import com.example.pruebatecnica.models.Usuario;
import com.example.pruebatecnica.services.AuthService;
import com.example.pruebatecnica.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class UsuarioSeeder implements ApplicationRunner {


    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Usuario admin = Usuario.builder()
                .nombre("ADMIN")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("password"))
                .fechaRegistro(LocalDateTime.now())
                .rol(Rol.ADMIN)
                .build();

        Usuario usuario = Usuario.builder()
                .nombre("USUARIO")
                .email("usuario@gmail.com")
                .password(passwordEncoder.encode("password"))
                .fechaRegistro(LocalDateTime.now())
                .rol(Rol.USUARIO)
                .build();

        List<Usuario> usuarios = new ArrayList<>();

        usuarios.add(admin);
        usuarios.add(usuario);

        usuarioService.postAllUsuarios(usuarios); //inserta el admin y el usuario al correrse la aplicacion
    }
}
