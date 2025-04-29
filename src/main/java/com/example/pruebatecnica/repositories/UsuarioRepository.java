package com.example.pruebatecnica.repositories;

import com.example.pruebatecnica.models.Usuario;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);//jpa interpetra la consulta gracias al existsBy seguido del campo
    boolean existsById(Long id);
    Optional<Usuario> findByEmail(String email);
}
