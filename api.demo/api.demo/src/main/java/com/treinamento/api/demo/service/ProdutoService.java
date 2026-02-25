package com.treinamento.api.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.treinamento.api.demo.model.Produto;
import com.treinamento.api.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;

	public Produto salvar(Produto produto) {
		return produtoRepository.salvar(produto);
	}

	public List<Produto> listarTodos() {
		return produtoRepository.listarTodos();
	}

	public Produto buscarPorId(Long id) {
		return produtoRepository.buscarPorId(id)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
	}

	public Produto atualizar(Long id, Produto produto) {
		return produtoRepository.atualizar(id, produto)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
	}

	public void deletar(Long id) {
		if (!produtoRepository.deletar(id)) {
			throw new RuntimeException("Produto não encontrado com id: " + id);
		}
	}
}