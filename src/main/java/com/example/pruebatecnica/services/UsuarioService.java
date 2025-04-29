package com.example.pruebatecnica.services;

import com.example.pruebatecnica.UpdateValidations.UsuarioDTO;
import com.example.pruebatecnica.exceptions.NotFoundExcpetion;
import com.example.pruebatecnica.exceptions.UniqueException;
import com.example.pruebatecnica.models.Usuario;
import com.example.pruebatecnica.repositories.UsuarioRepository;
import com.example.pruebatecnica.responses.UsuarioDeleted;
import lombok.RequiredArgsConstructor;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    public final UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario postUsuario(Usuario usuario) throws UniqueException {
        usuario.setFechaRegistro(LocalDateTime.now());
        if(usuarioRepository.existsByEmail(usuario.getEmail())){//si ya existe el email arroja la excepcion de Unique
            throw new UniqueException("El email no es válido");
        }

        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Long id, UsuarioDTO usuarioDTO) throws NotFoundExcpetion, UniqueException {
        Usuario usuarioBD = usuarioRepository.findById(id)
        .orElseThrow(() -> new NotFoundExcpetion("El usuario con el id: " + id + " no existe"));

        System.out.println(usuarioBD.getNombre());

        if (usuarioDTO.getNombre() != null && !usuarioDTO.getNombre().isEmpty()) {
            usuarioBD.setNombre(usuarioDTO.getNombre());
        }

        if (usuarioDTO.getEmail() != null && !usuarioDTO.getEmail().isEmpty()) {

            if(usuarioRepository.existsByEmail(usuarioDTO.getEmail())){
                throw new UniqueException("El email no es valido");
            }

            usuarioBD.setEmail(usuarioDTO.getEmail());
        }

        return usuarioRepository.save(usuarioBD);
    }

    public UsuarioDeleted deleteUsuario(Long id) throws NotFoundExcpetion {
        Usuario usuarioBD = usuarioRepository.findById(id)
        .orElseThrow(() -> new NotFoundExcpetion("El usuario con el id: " + id + " no existe"));
        usuarioRepository.delete(usuarioBD);

        return new UsuarioDeleted("El usuario con el id: " + id + " ha sido eliminado correctamente");
    }
}
