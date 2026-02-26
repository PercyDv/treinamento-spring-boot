package com.treinamento.api.demo.exception;

public class RegraNegocioException extends RuntimeException {
	 private static final long serialVersionUID = 1L;
    
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
    
    public static RegraNegocioException emailJaCadastrado(String email) {
        return new RegraNegocioException("Email " + email + " já está cadastrado");
    }
}