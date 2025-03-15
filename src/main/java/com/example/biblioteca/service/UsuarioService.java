package com.example.biblioteca.service;

import com.example.biblioteca.domain.Emprestimo;
import com.example.biblioteca.domain.Usuario;
import com.example.biblioteca.dto.UsuarioDTO;
import com.example.biblioteca.exception.UsuarioComEmprestimoException;
import com.example.biblioteca.exception.UsuarioNotFoundException;
import com.example.biblioteca.repository.EmprestimoRepository;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmprestimoRepository emprestimoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, EmprestimoRepository emprestimoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public UsuarioDTO salvar(UsuarioDTO dto) {
        Usuario usuario = new Usuario(null, dto.nome(), dto.email());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioSalvo);
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
        return new UsuarioDTO(usuario);
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dtoAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        usuario.setNome(dtoAtualizado.nome());
        usuario.setEmail(dtoAtualizado.email());
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuario(usuario);
        if (!emprestimos.isEmpty()) {
            throw new UsuarioComEmprestimoException(id);
        }

        usuarioRepository.deleteById(id);
    }
}
