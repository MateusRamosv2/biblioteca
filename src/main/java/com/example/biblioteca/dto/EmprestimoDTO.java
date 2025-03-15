package com.example.biblioteca.dto;

import com.example.biblioteca.domain.Emprestimo;
import java.time.LocalDate;

public record EmprestimoDTO(
        Long id,
        Long usuarioId,
        Long livroId,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
    public EmprestimoDTO(Emprestimo emprestimo) {
        this(
                emprestimo.getId(),
                emprestimo.getUsuario() != null ? emprestimo.getUsuario().getId() : null,
                emprestimo.getLivro() != null ? emprestimo.getLivro().getId() : null,
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()
        );
    }
}
