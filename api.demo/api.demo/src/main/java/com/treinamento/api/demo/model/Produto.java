package com.treinamento.api.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome do Porduto é Obrigatório")
	@Size(min = 3, max = 100, message = "Nome do Produto deve ter entre 3 e 100 caracteres")
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@NotNull(message = "Preço é obrigatório")
	@Min(value = 1.00, message = "valor mínimo é R$ 1,00")
	@Column(name = "preco")
	private Double preco;

	@NotNull(message = "Quantidade é obrigatória")
	@Min(value = 1, message = "Quantidade mínima de produtos é 1 item")
	@Max(value = 99, message = "Quantidade máxima de produtos é 99 itens")
	@Column(name = "quantidade")
	private Integer quantidade;
	
	public Produto() {}

	public Produto(Long id, String nome, Double preco, Integer quantidade) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
