package com.example.biblioteca.service;

import com.example.biblioteca.domain.Livro;
import com.example.biblioteca.dto.LivroDTO;
import com.example.biblioteca.exception.LivroNotFoundException;
import com.example.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroDTO salvar(LivroDTO dto) {
        Livro livro = new Livro(dto.id(), dto.titulo(), dto.autor(), dto.disponivel());
        Livro livroSalvo = livroRepository.save(livro);
        return new LivroDTO(livroSalvo);
    }

    public List<LivroDTO> listarTodos() {
        return livroRepository.findAll().stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList());
    }

    public LivroDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
        return new LivroDTO(livro);
    }

    public LivroDTO atualizar(Long id, LivroDTO dtoAtualizado) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + id));

        livro.setTitulo(dtoAtualizado.titulo());
        livro.setAutor(dtoAtualizado.autor());
        livro.setDisponivel(dtoAtualizado.disponivel());

        return new LivroDTO(livroRepository.save(livro));
    }

    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new LivroNotFoundException(id);
        }
        livroRepository.deleteById(id);
    }
}
