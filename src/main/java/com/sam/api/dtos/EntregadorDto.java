package com.sam.api.dtos;

import javax.validation.constraints.NotBlank;

import com.sam.api.entities.Entregador;

import lombok.Data;

@Data
public class EntregadorDto {

	@NotBlank
	private String nome;

	@NotBlank
	private String cpf;

	@NotBlank
	private String telefone;

	@NotBlank
	private String logradouro;

	@NotBlank
	private String numero;

	private String complemento;

	@NotBlank
	private String bairro;

	
	
	public EntregadorDto(Entregador entregador) {
		nome = entregador.getNome();
		cpf = entregador.getCpf();
		telefone = entregador.getTelefone();
		logradouro = entregador.getLogradouro();
		numero = entregador.getNumero();
		complemento = entregador.getComplemento();
		bairro = entregador.getBairro();
		
		}



	public EntregadorDto(@NotBlank String nome, @NotBlank String cpf, @NotBlank String telefone,
			@NotBlank String logradouro, @NotBlank String numero, String complemento, @NotBlank String bairro) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
	}
	
}
