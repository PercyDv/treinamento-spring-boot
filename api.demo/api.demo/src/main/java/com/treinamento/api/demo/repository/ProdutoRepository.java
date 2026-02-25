package com.treinamento.api.demo.repository;

import com.treinamento.api.demo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findByNome(String nome);

	boolean existsByNome(String nome);
}