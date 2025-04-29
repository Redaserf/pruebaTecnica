package com.example.pruebatecnica.requests;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank
    String nombre;
    @NotNull
    @Email(message = "El email debe ser valido")
    String email;
    @NotNull
    @Size(min = 8, message = "La password debe ser de al menos 8 caracteres")
    String password;



}
