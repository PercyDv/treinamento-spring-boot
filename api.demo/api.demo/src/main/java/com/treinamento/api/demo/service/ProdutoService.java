package com.treinamento.api.demo.service;

import com.treinamento.api.demo.model.Produto;
import com.treinamento.api.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;
    
    public Produto salvar(Produto produto) {
        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new RuntimeException("Nome já cadastrado!");
        }
        return produtoRepository.save(produto); 
    }
    
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }
    
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
    }
    
    public Produto atualizar(Long id, Produto produto) {
        Produto produtoExistente = buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com id:"));
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setQuantidade(produto.getQuantidade());
        return produtoRepository.save(produtoExistente);
    }
    
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }
    
    public Produto buscarPorNome(String nome) {
        return produtoRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com nome: " + nome));
    }
}