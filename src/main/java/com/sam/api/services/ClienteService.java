package com.sam.api.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sam.api.controllers.ClienteController;
import com.sam.api.dtos.ClienteDto;
import com.sam.api.entities.Cliente;
import com.sam.api.repositories.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public List<ClienteDto> listarTodos() {
		List<Cliente> resultado = clienteRepository.findAll();
		
		return resultado.stream().map(x -> new ClienteDto(x).add(
				WebMvcLinkBuilder
				.linkTo(ClienteController.class)
				.slash(x.getId())
				.withSelfRel()))
				.collect(Collectors.toList());
	}

	public ResponseEntity<Object> salvarCliente(Cliente cliente) {

		if (emailCadastrado(cliente.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já esta em uso");
		}
		cliente.setHoraRegistro(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
	}

	public ResponseEntity<Object> encontrarPorId(Long id) {
		
		
		
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		if (!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		clienteOptional.get().add(WebMvcLinkBuilder
				.linkTo((methodOn(ClienteController.class)
				.listarTodos()))
				.withRel("Lista de Clientes"));
		
		
		return ResponseEntity.status(HttpStatus.OK).body(clienteOptional.get());
	}
	
	@Transactional
	public ResponseEntity<Object> atualizarCliente(Cliente cliente, Long id) {
		
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		if (emailCadastrado(cliente.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já esta em uso, utilize outro.");
		}
		cliente.setId(clienteOptional.get().getId());
		cliente.setHoraRegistro(clienteOptional.get().getHoraRegistro());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
	}

	@Transactional
	public ResponseEntity<Object> deletarCliente(Long id) {
		Optional<Cliente> clienteOptional =  clienteRepository.findById(id);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		clienteRepository.deleteById(clienteOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso");
	}
	public boolean emailCadastrado(String email) {
		return clienteRepository.existsByEmailIgnoreCase(email);
	}

}
