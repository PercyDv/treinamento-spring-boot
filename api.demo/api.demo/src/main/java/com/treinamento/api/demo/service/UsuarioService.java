package com.treinamento.api.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treinamento.api.demo.model.Usuario;
import com.treinamento.api.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.salvar(usuario);
    }
    
    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }
    
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }
    
    public Usuario atualizar(Long id, Usuario usuario) {
        return usuarioRepository.atualizar(id, usuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }
    
    public void deletar(Long id) {
        if (!usuarioRepository.deletar(id)) {
            throw new RuntimeException("Usuário não encontrado com id: " + id);
        }
    }
}