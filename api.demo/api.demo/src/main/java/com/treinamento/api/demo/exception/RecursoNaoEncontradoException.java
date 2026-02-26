package com.treinamento.api.demo.exception;

public class RecursoNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public static RecursoNaoEncontradoException paraUsuario(Long id) {
		return new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado");
	}

	public static RecursoNaoEncontradoException paraProduto(Long id) {
		return new RecursoNaoEncontradoException("Produto com ID " + id + " não encontrado");
	}
}