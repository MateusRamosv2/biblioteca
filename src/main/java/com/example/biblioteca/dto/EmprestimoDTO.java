package com.example.biblioteca.dto;

import com.example.biblioteca.domain.Emprestimo;

import java.time.LocalDate;

public class EmprestimoDTO {

    private Long id;
    private UsuarioDTO usuario;
    private LivroDTO livro;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public EmprestimoDTO() {}

    public EmprestimoDTO(Emprestimo emprestimo) {
        this.id = emprestimo.getId();
        this.usuario = new UsuarioDTO(emprestimo.getUsuario());
        this.livro = new LivroDTO(emprestimo.getLivro());
        this.dataEmprestimo = emprestimo.getDataEmprestimo();
        this.dataDevolucao = emprestimo.getDataDevolucao();
    }

    public Long getId() { return id; }
    public UsuarioDTO getUsuario() { return usuario; }
    public LivroDTO getLivro() { return livro; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
}
