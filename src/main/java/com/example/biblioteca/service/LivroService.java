package com.example.biblioteca.service;

import com.example.biblioteca.domain.Livro;
import com.example.biblioteca.dto.LivroDTO;
import com.example.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroDTO salvar(LivroDTO dto) {
        Livro livro = new Livro(dto.getId(), dto.getTitulo(), dto.getAutor(), dto.isDisponivel());
        Livro livroSalvo = livroRepository.save(livro);
        return new LivroDTO(livroSalvo);
    }

    public List<LivroDTO> listarTodos() {
        return livroRepository.findAll().stream()
                .map(LivroDTO::new) // Converte cada entidade Livro em DTO
                .collect(Collectors.toList());
    }

    public Optional<LivroDTO> buscarPorId(Long id) {
        return livroRepository.findById(id).map(LivroDTO::new);
    }

    public LivroDTO atualizar(Long id, LivroDTO dtoAtualizado) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + id));

        livro.setTitulo(dtoAtualizado.getTitulo());
        livro.setAutor(dtoAtualizado.getAutor());
        livro.setDisponivel(dtoAtualizado.isDisponivel());
        return new LivroDTO(livroRepository.save(livro));
    }

    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }
}