package com.example.biblioteca.dto;

import com.example.biblioteca.domain.Livro;

public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public LivroDTO() {}

    public LivroDTO(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.disponivel = livro.isDisponivel();
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponivel() { return disponivel; }
}
