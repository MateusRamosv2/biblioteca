package com.example.biblioteca.exception;

public class UsuarioComEmprestimoException extends BibliotecaException {
    public UsuarioComEmprestimoException(Long id) {
        super("Usuário com ID " + id + " possui empréstimos pendentes e não pode ser deletado.");
    }
}





/*Isso aqui é um exceção pra avisar  Exceção para empréstimo pendente ao tentar excluir usuário*/

