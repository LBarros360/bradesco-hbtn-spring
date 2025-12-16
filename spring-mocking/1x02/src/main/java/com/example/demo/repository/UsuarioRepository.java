package com.example.demo.repository;

import com.example.demo.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

}
