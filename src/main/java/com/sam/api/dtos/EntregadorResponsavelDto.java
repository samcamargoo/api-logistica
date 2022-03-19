package com.sam.api.dtos;

import com.sam.api.entities.Entregador;

import lombok.Data;

@Data
public class EntregadorResponsavelDto {

	
	private String nome;

	
	private String cpf;

	
	private String telefone;

	

	public EntregadorResponsavelDto(String nome, String cpf, String telefone) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}



	public EntregadorResponsavelDto(Entregador entregador) {
		nome = entregador.getNome();
		cpf = entregador.getCpf();
		telefone = entregador.getTelefone();

	}

}
