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

    @GetMapping
    public ResponseEntity<List<EmprestimoDTO>> listarEmprestimos() {
        return ResponseEntity.ok(emprestimoService.listarTodos());
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


    /*Este DeleteMapping foi implementado depois de eu registrar as devoluções de
    livro, ou seja, devolução está funcionando. O problema agora está em deletar
    emprestimo. Depois eu tenho que testar deletar livro, usuario também.
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable Long id) {
        emprestimoService.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }


}
