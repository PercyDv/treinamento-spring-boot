package com.treinamento.api.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.treinamento.api.demo.model.Usuario;

@Repository
public class UsuarioRepository {
    
    private List<Usuario> usuarios = new ArrayList<>();
    private Long nextId = 1L;
    
    public Usuario salvar(Usuario usuario) {
        usuario.setId(nextId++);
        usuarios.add(usuario);
        return usuario;
    }
    
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }
    
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarios.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
    
    public Optional<Usuario> atualizar(Long id, Usuario usuarioAtualizado) {
        return buscarPorId(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setIdade(usuarioAtualizado.getIdade());
            return usuario;
        });
    }
    
    public boolean deletar(Long id) {
        return usuarios.removeIf(u -> u.getId().equals(id));
    }
}