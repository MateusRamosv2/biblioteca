package com.example.biblioteca.service;

import com.example.biblioteca.domain.Emprestimo;
import com.example.biblioteca.domain.Livro;
import com.example.biblioteca.domain.Usuario;
import com.example.biblioteca.dto.EmprestimoDTO;
import com.example.biblioteca.repository.EmprestimoRepository;
import com.example.biblioteca.repository.LivroRepository;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public EmprestimoDTO toDTO(Emprestimo emprestimo) {
        return new EmprestimoDTO(emprestimo);
    }

    /*public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
        Usuario usuario = usuarioRepository.findById(emprestimo.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + emprestimo.getUsuario().getId()));

        Livro livro = livroRepository.findById(emprestimo.getLivro().getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + emprestimo.getLivro().getId()));

        if (!livro.isDisponivel()) {
            throw new RuntimeException("O livro não está disponível para empréstimo.");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);

        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        return emprestimoRepository.save(emprestimo);
    }
     */


    public Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
        Usuario usuario = usuarioRepository.findById(emprestimo.getUsuario().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + emprestimo.getUsuario().getId()));

        Livro livro = livroRepository.findById(emprestimo.getLivro().getId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + emprestimo.getLivro().getId()));

        if (!livro.isDisponivel()) {
            throw new RuntimeException("O livro não está disponível para empréstimo.");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);

        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        return emprestimoRepository.save(emprestimo);
    }


    public List<EmprestimoDTO> listarTodos() {
        return emprestimoRepository.findAll().stream()
                .map(EmprestimoDTO::new)
                .collect(Collectors.toList());
    }



    public Emprestimo registrarDevolucao(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com id: " + id)); // Se não encontrar, lança erro

        if (emprestimo.getDataDevolucao() != null) {
            throw new RuntimeException("Este empréstimo já foi devolvido.");
        }

        emprestimo.setDataDevolucao(LocalDate.now());

        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }








}
