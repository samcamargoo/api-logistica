package com.sam.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sam.api.entities.Entregador;
import com.sam.api.entities.PontoEletronico;

public interface PontoEletronicoRepository extends JpaRepository<PontoEletronico, Long> {

	boolean existsByCpf(String cpf);
	Entregador findByCpf(String cpf);
}
