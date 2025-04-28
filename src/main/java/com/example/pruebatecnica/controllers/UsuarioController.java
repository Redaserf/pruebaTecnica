package com.example.pruebatecnica.controllers;

import com.example.pruebatecnica.UpdateValidations.UsuarioDTO;
import com.example.pruebatecnica.exceptions.NotFoundExcpetion;
import com.example.pruebatecnica.exceptions.UniqueException;
import com.example.pruebatecnica.models.Usuario;
import com.example.pruebatecnica.responses.UsuarioDeleted;
import com.example.pruebatecnica.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios(){
        return new ResponseEntity<>(usuarioService.getAllUsuarios(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> postUsuario(@RequestBody @Valid Usuario usuario) throws UniqueException {
        return new ResponseEntity<>(usuarioService.postUsuario(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> putUsuario(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) throws NotFoundExcpetion, UniqueException {
        return new ResponseEntity<>(usuarioService.updateUsuario(id, usuarioDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioDeleted> deleteUsuario(@PathVariable Long id) throws NotFoundExcpetion {
        return new ResponseEntity<>(usuarioService.deleteUsuario(id), HttpStatus.OK);
    }

}
