package com.example.biblioteca.dto;

import com.example.biblioteca.domain.Emprestimo;

import java.time.LocalDate;

public class EmprestimoDTO {

    private Long id;
    private Long usuarioId;
    private Long livroId;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public EmprestimoDTO() {
    }

    public EmprestimoDTO(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.usuarioId = emprestimo.getUsuario() != null ? emprestimo.getUsuario().getId() : null;
        this.livroId = emprestimo.getLivro() != null ? emprestimo.getLivro().getId() : null;
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataDevolucao = emprestimo.getDataDevolucao();
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }


}
