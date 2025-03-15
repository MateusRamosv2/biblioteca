package com.example.biblioteca.dto;

import com.example.biblioteca.domain.Livro;

public record LivroDTO(
        Long id,
        String titulo,
        String autor,
        boolean disponivel
) {
    public LivroDTO(Livro livro) {
        this(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.isDisponivel()
        );
    }
}
