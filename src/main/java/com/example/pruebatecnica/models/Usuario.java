package com.example.pruebatecnica.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;
    @Column(unique = true, nullable = false)

    @Email(message = "El email debe ser valido")
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime fechaRegistro;
}
