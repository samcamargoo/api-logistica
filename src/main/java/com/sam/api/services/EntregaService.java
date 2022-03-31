package com.sam.api.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sam.api.controllers.EntregaController;
import com.sam.api.dtos.EntregaDto;
import com.sam.api.entities.Entrega;
import com.sam.api.enums.StatusEntrega;
import com.sam.api.repositories.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService {

	private EntregaRepository entregaRepository;

	public List<EntregaDto> listarTodasEntregas() {
		List<Entrega> entregas = entregaRepository.findAll();
		return entregas.stream()
				 .map(x -> new EntregaDto(x).add(
				  WebMvcLinkBuilder
				 .linkTo(EntregaController.class)
				 .slash(x.getId())
				 .withSelfRel()))
				 .collect(Collectors.toList());
	}

	@Transactional
	public ResponseEntity<Object> criarEntrega(Entrega entrega) {

		String horaLimiteString = "16:00:00";
		LocalTime horaLimite = LocalTime.parse(horaLimiteString);
		LocalTime localTime = LocalTime.now();

		if (localTime.isAfter(horaLimite)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Entregas só podem ser criadas até 16:00");
		}

		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setHoraCriada(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(entregaRepository.save(entrega));

	}

	@Transactional
	public ResponseEntity<Object> deletarEntrega(Long id) {

		Optional<Entrega> entregaOptional = entregaRepository.findById(id);

		if (!entregaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entrega não encontrada");
		}

		entregaRepository.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Entrega excluida com sucesso");
	}

	public ResponseEntity<Object> encontrarPorId(Long id) {

		Optional<Entrega> entregaOptional = entregaRepository.findById(id);

		if (!entregaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entrega não encontrada");
		}

		List<EntregaDto> entrega = entregaOptional.stream()
				.map(x -> new EntregaDto(x).add(
				 WebMvcLinkBuilder
				.linkTo(methodOn(EntregaController.class).listarTodasEntregas())
				.withRel("Lista de Entregas")))
				.collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(entrega);
	}

	@Transactional
	public ResponseEntity<Object> cancelarEntrega(Long id) {

		Optional<Entrega> entregaOptional = entregaRepository.findById(id);

		if (entregaOptional.get().getStatus().equals(StatusEntrega.CANCELADA)) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Entrega já foi cancelada previamente");
		}

		if (!entregaOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Entrega não encontrada");
		}
		var entrega = new Entrega();
		entrega.setId(entregaOptional.get().getId());
		entrega.setCliente(entregaOptional.get().getCliente());
		entrega.setDestinatario(entregaOptional.get().getDestinatario());
		entrega.setEntregador(entregaOptional.get().getEntregador());
		entrega.setHoraCriada(entregaOptional.get().getHoraCriada());
		entrega.setHoraFinalizada(LocalDateTime.now());
		entrega.setStatus(StatusEntrega.CANCELADA);
		entregaRepository.save(entrega);
		return ResponseEntity.status(HttpStatus.OK).body("Entrega cancelada com sucesso");

	}

	@Transactional
	public ResponseEntity<Object> finalizar(Long id) {

		Optional<Entrega> entregaOptional = entregaRepository.findById(id);

		if (entregaOptional.get().naoPodeSerFinalizada()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Entrega não pode ser finalizada");
		}

		var entrega = new Entrega();
		entrega.setId(entregaOptional.get().getId());
		entrega.setDestinatario(entregaOptional.get().getDestinatario());
		entrega.setEntregador(entregaOptional.get().getEntregador());
		entrega.setHoraCriada(entregaOptional.get().getHoraCriada());
		entrega.setStatus(StatusEntrega.FINALIZADA);
		entrega.setHoraFinalizada(LocalDateTime.now());
		entregaRepository.save(entrega);
		return ResponseEntity.status(HttpStatus.OK).body("Entrega finalizada com sucesso");
	}

}
