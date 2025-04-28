package com.example.pruebatecnica.UpdateValidations;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioDTO {

    private String nombre;

    @Email(message = "El email debe ser valido")
    private String email;
}
