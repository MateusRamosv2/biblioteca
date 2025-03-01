package com.example.biblioteca.controller;

import com.example.biblioteca.dto.LivroDTO;
import com.example.biblioteca.service.LivroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroDTO> adicionarLivro(@RequestBody LivroDTO livroDTO) {
        return ResponseEntity.status(201).body(livroService.salvar(livroDTO));
    }

    @GetMapping
    public ResponseEntity<List<LivroDTO>> listarLivros() {
        return ResponseEntity.ok(livroService.listarTodos());
    }
}
