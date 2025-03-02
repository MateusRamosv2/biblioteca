package com.example.biblioteca.exception;

public class UsuarioNotFoundException extends BibliotecaException {
    public UsuarioNotFoundException(Long id) {
        super("Usuário com ID " + id + " não encontrado.");
    }
}


/*Isso aqui é um exceção pra avisar quando um usuário não aparecer*/