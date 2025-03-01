package com.example.biblioteca.repository;

import com.example.biblioteca.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {}
