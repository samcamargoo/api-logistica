package com.sam.api.entities;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
