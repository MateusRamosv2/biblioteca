package com.example.biblioteca.controller;

import com.example.biblioteca.domain.Emprestimo;
import com.example.biblioteca.dto.EmprestimoDTO;
import com.example.biblioteca.service.EmprestimoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")

public class EmprestimoController {

    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> buscarEmprestimoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(emprestimoService.buscarPorId(id));
    }


    @PostMapping
    public ResponseEntity<EmprestimoDTO> criarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        EmprestimoDTO emprestimoSalvo = emprestimoService.realizarEmprestimo(emprestimoDTO);
        return ResponseEntity.ok(emprestimoSalvo);
    }

    @PutMapping("/{id}/devolucao")
    public ResponseEntity<EmprestimoDTO> registrarDevolucao(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoService.registrarDevolucao(id);
        return ResponseEntity.ok(new EmprestimoDTO(emprestimo));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Long id) {
        emprestimoService.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmprestimoDTO>> listarEmprestimos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
    }



}
