package com.example.biblioteca.exception;

public class UsuarioNotFoundException extends BibliotecaException {
    public UsuarioNotFoundException(Long id) {
        super("Usuário com ID " + id + " não encontrado.");
    }
}
