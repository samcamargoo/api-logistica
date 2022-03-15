package com.sam.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sam.api.entities.Cliente;
import com.sam.api.repositories.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	private ClienteRepository clienteRepository;

	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
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
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findById(id));
	}
	
	public ResponseEntity<Object> atualizarCliente(Cliente cliente, Long id) {
		
		Optional<Cliente> clienteOptional = clienteRepository.findById(id);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Cliente não encontrado");
		}
		if (emailCadastrado(cliente.getEmail())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já esta em uso, utilize outro.");
		}
		cliente.setId(clienteOptional.get().getId());
		cliente.setHoraRegistro(clienteOptional.get().getHoraRegistro());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
	}

	public boolean emailCadastrado(String email) {
		return clienteRepository.existsByEmailIgnoreCase(email);
	}

}
