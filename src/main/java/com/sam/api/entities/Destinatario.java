package com.sam.api.entities;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Destinatario {


	private String nome;

	private String logradouro;

	private String numero;

	
	private String complemento;

	private String bairro;
}
