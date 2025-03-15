package com.example.biblioteca.exception;

public class LivroNotFoundException extends BibliotecaException {
    public LivroNotFoundException(Long id) {
        super("Livro com ID " + id + " não encontrado.");
    }
}
