package com.example.biblioteca.exception;

public class UsuarioComEmprestimoException extends BibliotecaException {
    public UsuarioComEmprestimoException(Long id) {
        super("Usuário com ID " + id + " possui empréstimos pendentes e não pode ser deletado.");
    }
}


