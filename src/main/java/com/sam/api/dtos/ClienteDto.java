package com.sam.api.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClienteDto {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String telefone;
}
