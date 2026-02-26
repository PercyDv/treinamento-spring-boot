package com.treinamento.api.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	@Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
	private String nome;

	@NotBlank(message = "Email é obrigatório")
    @Email(message = "Email deve ser válido")
    @Column(unique = true, nullable = false)
	private String email;
	
    @Min(value = 18, message = "Idade mínima é 18 anos")
	private Integer idade;

	@NotBlank(message = "Senha é obrigatória")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Column(nullable = false)
    @JsonIgnore
	private String senha;

	@Column(nullable = false)
	private String role = "USER";

}