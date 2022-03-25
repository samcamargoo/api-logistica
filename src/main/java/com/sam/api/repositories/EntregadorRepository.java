package com.sam.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.api.entities.Entregador;

public interface EntregadorRepository extends JpaRepository<Entregador, Long> {

	boolean existsByCpf(String cpf);
	Entregador findByCpf(String cpf);
	
}
