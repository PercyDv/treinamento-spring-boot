package com.treinamento.api.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroResposta> handleValidationExceptions(MethodArgumentNotValidException ex,
			HttpServletRequest request) {

		ErroResposta erro = new ErroResposta(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Erro de validação",
				"Um ou mais campos estão inválidos", request.getRequestURI());

		ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
			erro.adicionarErro(fieldError.getField(), fieldError.getDefaultMessage());
		});

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(RecursoNaoEncontradoException.class)
	public ResponseEntity<ErroResposta> handleNotFound(RecursoNaoEncontradoException ex, HttpServletRequest request) {

		ErroResposta erro = new ErroResposta(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Recurso não encontrado", ex.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(RegraNegocioException.class)
	public ResponseEntity<ErroResposta> handleBusinessRule(RegraNegocioException ex, HttpServletRequest request) {

		ErroResposta erro = new ErroResposta(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Violação de regra de negócio", ex.getMessage(), request.getRequestURI());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErroResposta> handleGenericException(Exception ex, HttpServletRequest request) {

		ErroResposta erro = new ErroResposta(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
				"Erro interno no servidor", "Ocorreu um erro inesperado. Tente novamente mais tarde.",
				request.getRequestURI());

		ex.printStackTrace();

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
	}
}