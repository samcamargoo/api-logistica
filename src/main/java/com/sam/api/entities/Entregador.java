package com.sam.api.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_ENTREGADORES")
public class Entregador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@CPF(message = "Cpf inválido")
	@Column(nullable = false, unique = true)
	private String cpf;

	@Column(nullable = false)
	private String telefone;

	@Column(nullable = false)
	private String logradouro;

	@Column(nullable = false)
	private String numero;

	@Column
	private String complemento;

	@Column(nullable = false)
	private String bairro;
	
	
	
	private LocalDateTime horaRegistro;
	
	@OneToMany(mappedBy = "entregador")
	private List<Entrega> entrega = new ArrayList<>();
}
