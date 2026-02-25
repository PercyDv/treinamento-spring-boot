package com.treinamento.api.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.treinamento.api.demo.model.Produto;

@Repository
public class ProdutoRepository {
    
    private List<Produto> produtos = new ArrayList<>();
    private Long nextId = 1L;
    
    public Produto salvar(Produto produto) {
        produto.setId(nextId++);
        produtos.add(produto);
        return produto;
    }
    
    public List<Produto> listarTodos() {
        return new ArrayList<>(produtos);
    }
    
    public Optional<Produto> buscarPorId(Long id) {
        return produtos.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }
    
    public Optional<Produto> atualizar(Long id, Produto produtoAtualizado) {
        return buscarPorId(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setQuantidade(produtoAtualizado.getQuantidade());
            return produto;
        });
    }
    
    public boolean deletar(Long id) {
        return produtos.removeIf(u -> u.getId().equals(id));
    }
}