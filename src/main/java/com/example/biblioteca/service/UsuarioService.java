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
import java.util.Optional;
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
        Usuario usuario = new Usuario(null, dto.getNome(), dto.getEmail());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioSalvo);
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioDTO::new);
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dtoAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));

        usuario.setNome(dtoAtualizado.getNome());
        usuario.setEmail(dtoAtualizado.getEmail());
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



/*package com.example.biblioteca.service;

import com.example.biblioteca.domain.Emprestimo;
import com.example.biblioteca.domain.Usuario;
import com.example.biblioteca.dto.UsuarioDTO;
import com.example.biblioteca.repository.EmprestimoRepository;
import com.example.biblioteca.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmprestimoRepository emprestimoRepository; // Adicionando repository de empréstimos

    public UsuarioService(UsuarioRepository usuarioRepository, EmprestimoRepository emprestimoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    public UsuarioDTO salvar(UsuarioDTO dto) {
        Usuario usuario = new Usuario(null, dto.getNome(), dto.getEmail());
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioSalvo);
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll().stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return usuarioRepository.findById(id).map(UsuarioDTO::new);
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO dtoAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));

        usuario.setNome(dtoAtualizado.getNome());
        usuario.setEmail(dtoAtualizado.getEmail());
        return new UsuarioDTO(usuarioRepository.save(usuario));
    }

    public void deletar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário com id " + id + " não encontrado."));


        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuario(usuario);
        if (!emprestimos.isEmpty()) {
            throw new RuntimeException("Usuário possui empréstimos pendentes e não pode ser deletado.");
        }

        usuarioRepository.deleteById(id);
    }
}

 */
