package com.sam.api.dtos;

import com.sam.api.entities.Destinatario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DestinatarioDto {
	
	private String nome;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	public DestinatarioDto(String nome, String logradouro, String numero, String complemento, String bairro) {
		super();
		this.nome = nome;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
	}
	
	public DestinatarioDto(Destinatario destinatario) {
		super();
		nome = destinatario.getNome();
		logradouro = destinatario.getLogradouro();
		numero = destinatario.getNumero();
		complemento = destinatario.getComplemento();
		bairro = destinatario.getBairro();
	}
	
	

	
}
