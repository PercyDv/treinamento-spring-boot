package com.treinamento.api.demo.service;

import com.treinamento.api.demo.model.Usuario;
import com.treinamento.api.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email já cadastrado!");
        }
        return usuarioRepository.save(usuario); 
    }
    
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }
    
    public Usuario atualizar(Long id, Usuario usuario) {
        Usuario usuarioExistente = buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id:"));
        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setIdade(usuario.getIdade());
        return usuarioRepository.save(usuarioExistente);
    }
    
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
    
    public Usuario buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com email: " + email));
    }
}