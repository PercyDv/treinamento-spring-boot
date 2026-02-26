package com.treinamento.api.demo.service;

import com.treinamento.api.demo.exception.RecursoNaoEncontradoException;
import com.treinamento.api.demo.exception.RegraNegocioException;
import com.treinamento.api.demo.model.Usuario;
import com.treinamento.api.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    @Transactional
    public Usuario salvar(Usuario usuario) {
        log.info("Salvando usuário: {}", usuario.getEmail());
        
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RegraNegocioException("Email já cadastrado: " + usuario.getEmail());
        }
        
        return usuarioRepository.save(usuario);
    }
    
    public List<Usuario> listarTodos() {
        log.info("Listando todos os usuários");
        return usuarioRepository.findAll();
    }
    
    public Usuario buscarPorId(Long id) {
        log.info("Buscando usuário por ID: {}", id);
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id));
    }
    
    @Transactional
    public Usuario atualizar(Long id, Usuario usuario) {
        log.info("Atualizando usuário ID: {}", id);
        
        Usuario usuarioExistente = buscarPorId(id);
        
        if (!usuarioExistente.getEmail().equals(usuario.getEmail()) &&
            usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RegraNegocioException("Email já cadastrado: " + usuario.getEmail());
        }
        
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setIdade(usuario.getIdade());
        
        return usuarioRepository.save(usuarioExistente);
    }
    
    @Transactional
    public void deletar(Long id) {
        log.info("Deletando usuário ID: {}", id);
        
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário não encontrado com id: " + id);
        }
        
        usuarioRepository.deleteById(id);
    }
    
    public Usuario buscarPorEmail(String email) {
        log.info("Buscando usuário por email: {}", email);
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não encontrado com email: " + email));
    }
}