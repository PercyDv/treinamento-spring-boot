package com.treinamento.api.demo.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ErroResposta {

	private LocalDateTime timestamp;
	private Integer status;
	private String titulo;
	private String mensagem;
	private String caminho;
	private List<ErroValidacao> erros = new ArrayList<>();

	public ErroResposta(LocalDateTime timestamp, Integer status, String titulo, String mensagem, String caminho) {
		this.timestamp = timestamp;
		this.status = status;
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.caminho = caminho;
		;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getCaminho() {
		return caminho;
	}

	public List<ErroValidacao> getErros() {
		return erros;
	}

	public void adicionarErro(String campo, String mensagem) {
		this.erros.add(new ErroValidacao(campo, mensagem));
	}

	public static class ErroValidacao {
		private String mensagem;
		private String campo;

		public ErroValidacao(String campo, String mensagem) {
			this.campo = campo;
			this.mensagem = mensagem;
		}

		public String getCampo() {
			return campo;
		}

		public String getMensagem() {
			return mensagem;
		}

	}
}