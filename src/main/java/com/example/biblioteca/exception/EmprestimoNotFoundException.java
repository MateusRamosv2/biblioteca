package com.example.biblioteca.exception;

public class EmprestimoNotFoundException extends BibliotecaException {
    public EmprestimoNotFoundException(Long id) {
        super("Empréstimo com ID " + id + " não encontrado.");
    }
}
