package com.example.biblioteca.service;

import com.example.biblioteca.domain.Emprestimo;
import com.example.biblioteca.domain.Livro;
import com.example.biblioteca.domain.Usuario;
import com.example.biblioteca.dto.EmprestimoDTO;
import com.example.biblioteca.exception.EmprestimoNotFoundException;
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

    public EmprestimoDTO realizarEmprestimo(EmprestimoDTO emprestimoDTO) {
        Usuario usuario = usuarioRepository.findById(emprestimoDTO.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + emprestimoDTO.usuarioId()));

        Livro livro = livroRepository.findById(emprestimoDTO.livroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + emprestimoDTO.livroId()));

        if (!livro.isDisponivel()) {
            throw new RuntimeException("O livro não está disponível para empréstimo.");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(emprestimoDTO.dataEmprestimo());

        emprestimo = emprestimoRepository.save(emprestimo);

        return new EmprestimoDTO(emprestimo);
    }

    public List<EmprestimoDTO> listarTodos() {
        return emprestimoRepository.findAll().stream()
                .map(EmprestimoDTO::new)
                .collect(Collectors.toList());
    }

    public Emprestimo registrarDevolucao(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado com id: " + id));

        if (emprestimo.getDataDevolucao() != null) {
            throw new RuntimeException("Este empréstimo já foi devolvido.");
        }

        emprestimo.setDataDevolucao(LocalDate.now());

        Livro livro = emprestimo.getLivro();
        livro.setDisponivel(true);
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }

    public void deletarEmprestimo(Long id) {
        if (!emprestimoRepository.existsById(id)) {
            throw new EmprestimoNotFoundException(id);
        }
        emprestimoRepository.deleteById(id);
    }

    public EmprestimoDTO buscarPorId(Long id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new EmprestimoNotFoundException(id));

        return new EmprestimoDTO(emprestimo);
    }
}
