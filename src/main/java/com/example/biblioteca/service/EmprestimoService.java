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

    public EmprestimoDTO realizarEmprestimo(EmprestimoDTO emprestimoDTO) {
        Usuario usuario = usuarioRepository.findById(emprestimoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + emprestimoDTO.getUsuarioId()));

        Livro livro = livroRepository.findById(emprestimoDTO.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com id: " + emprestimoDTO.getLivroId()));

        if (!livro.isDisponivel()) {
            throw new RuntimeException("O livro não está disponível para empréstimo.");
        }

        livro.setDisponivel(false);
        livroRepository.save(livro);

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(emprestimoDTO.getDataEmprestimo());

        emprestimo = emprestimoRepository.save(emprestimo);

        return new EmprestimoDTO(emprestimo);
    }

    /*
     * Código antigo:
     * - O método `realizarEmprestimo` recebia um objeto `Emprestimo` diretamente no corpo da requisição.
     * - Isso causava erro ao tentar converter `usuarioId` e `livroId`.
     *
     * Alteração:
     * - Agora recebe um `EmprestimoDTO` contendo apenas os IDs.
     * - Busca os objetos `Usuario` e `Livro` pelo repositório antes de criar o empréstimo.
     */

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

    /*Este DeleteMapping foi implementado depois de eu registrar as devoluções de
    livro, ou seja, devolução está funcionando. O problema agora está em deletar
    emprestimo. Depois eu tenho que testar deletar livro, usuario também.
     */


    public void deletarEmprestimo(Long id) {
        if (!emprestimoRepository.existsById(id)) {
            throw new RuntimeException("Empréstimo não encontrado");
        }
        emprestimoRepository.deleteById(id);
    }


}
