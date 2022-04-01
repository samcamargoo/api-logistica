package com.sam.api.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiError {

	private HttpStatus status;
	private String mensagem;
	private List<String> erros;

	public ApiError(HttpStatus status, String mensagem, List<String> erros) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		this.erros = erros;
	}

	public ApiError(HttpStatus status, String mensagem, String erro) {
		super();
		this.status = status;
		this.mensagem = mensagem;
		erros = Arrays.asList(erro);

	}
}