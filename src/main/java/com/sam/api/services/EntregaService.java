package com.sam.api.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sam.api.entities.Entrega;
import com.sam.api.enums.EntregaEnum;
import com.sam.api.repositories.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService {

	private EntregaRepository entregaRepository;

	public List<Entrega> listarTodasEntregas() {
		return entregaRepository.findAll();
	}

	public ResponseEntity<Object> criarEntrega(Entrega entrega) {
		/*
		 * TODO realizar as verificaçoes para se criar uma entrega
		 */

		entrega.setStatus(EntregaEnum.PENDENTE);
		entrega.setHoraCriada(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(entregaRepository.save(entrega));

	}

}
