package com.example.biblioteca.dto;

import com.example.biblioteca.domain.Usuario;

public record UsuarioDTO(
        Long id,
        String nome,
        String email
) {
    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}
