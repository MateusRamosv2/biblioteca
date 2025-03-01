package com.example.biblioteca.repository;

import com.example.biblioteca.domain.Emprestimo;
import com.example.biblioteca.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByUsuario(Usuario usuario);  // Busca empréstimos por usuário
}
