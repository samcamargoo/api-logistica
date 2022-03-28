package com.sam.api.dtos;

import javax.validation.constraints.NotBlank;

import org.springframework.hateoas.RepresentationModel;

import com.sam.api.entities.Cliente;

import lombok.Data;

@Data
public class ClienteDto extends RepresentationModel<ClienteDto> {


	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String telefone;

	public ClienteDto( @NotBlank String nome, @NotBlank String email, @NotBlank String telefone) {
		super();
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
	}
	
	public ClienteDto(Cliente cliente) {
		nome = cliente.getNome();
		email = cliente.getEmail();
		telefone = cliente.getTelefone();
	}
}
