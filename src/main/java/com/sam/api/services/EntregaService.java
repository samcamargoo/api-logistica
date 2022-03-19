package com.sam.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sam.api.dtos.EntregaDto;
import com.sam.api.entities.Entrega;
import com.sam.api.enums.EntregaEnum;
import com.sam.api.repositories.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService {

	private EntregaRepository entregaRepository;

	public List<EntregaDto> listarTodasEntregas() {
		List<Entrega> entregas = entregaRepository.findAll();
		return entregas.stream().map(x -> new EntregaDto(x)).collect(Collectors.toList());
	}

	public ResponseEntity<Object> criarEntrega(Entrega entrega) {
		/*
		 * TODO realizar as verificaçoes para se criar uma entrega
		 */

		entrega.setStatus(EntregaEnum.PENDENTE);
		entrega.setHoraCriada(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(entregaRepository.save(entrega));

	}
	
	public ResponseEntity<Object> deletarEntrega(Long id) {
		
		Optional<Entrega> entregaOptional = entregaRepository.findById(id);
		
		if(!entregaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entrega não encontrada");
		}
		
		entregaRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Entrega excluida com sucesso");
	}

}
