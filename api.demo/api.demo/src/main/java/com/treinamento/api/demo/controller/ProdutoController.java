package com.treinamento.api.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.api.demo.model.Produto;
import com.treinamento.api.demo.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService ProdutoService;
    
    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody Produto produto) {
        Produto novoProduto = ProdutoService.salvar(produto);
        return new ResponseEntity<>(novoProduto, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        List<Produto> produtos = ProdutoService.listarTodos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Produto produto = ProdutoService.buscarPorId(id);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        Produto produtoAtualizado = ProdutoService.atualizar(id, produto);
        return new ResponseEntity<>(produtoAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ProdutoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}