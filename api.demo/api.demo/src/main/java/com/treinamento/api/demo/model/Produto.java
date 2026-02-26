package com.treinamento.api.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "nome do produto é obrigatório")
	@Size(min = 3, max = 100)
	@Column(nullable = false, length = 100)
	private String nome;

	@NotNull
	@Min(value = 1, message = "Valor mínimo é 1 real")
	private Double preco;

	@NotNull
	@Min(1)
	@Max(99)
	private Integer quantidade;
	
}
