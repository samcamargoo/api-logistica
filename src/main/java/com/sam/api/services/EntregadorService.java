package com.sam.api.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sam.api.dtos.EntregadorDto;
import com.sam.api.entities.Entregador;
import com.sam.api.repositories.EntregadorRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregadorService {

	private EntregadorRepository entregadorRepository;
	
	public List<EntregadorDto> listarTodos() {
		
		List<Entregador> resultado = entregadorRepository.findAll();
		return resultado.stream().map(x -> new EntregadorDto(x)).collect(Collectors.toList());
	}

	@Transactional
	public ResponseEntity<Object> salvarEntregador(Entregador entregador) {

		if (cpfCadastrado(entregador.getCpf())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já está cadastrado");
		}
		entregador.setHoraRegistro(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.CREATED).body(entregadorRepository.save(entregador));
	}

	public ResponseEntity<Object> encontrarPorId(Long id) {

		Optional<Entregador> entregadorOptional = entregadorRepository.findById(id);
		if (!entregadorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(entregadorRepository.findById(id));
	}

	@Transactional
	public ResponseEntity<Object> atualizarEntregador(Entregador entregador, Long id) {

		Optional<Entregador> entregadorOptional = entregadorRepository.findById(id);

		if (!entregadorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entregador não encontrado");
		}
		if (cpfCadastrado(entregador.getCpf())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF já está cadastrado.");
		}

		entregador.setId(entregadorOptional.get().getId());
		entregador.setNome(entregador.getNome());
		entregador.setCpf(entregadorOptional.get().getCpf());
		entregador.setTelefone(entregador.getTelefone());
		entregador.setLogradouro(entregador.getLogradouro());
		entregador.setNumero(entregador.getNumero());
		entregador.setComplemento(entregador.getComplemento());
		entregador.setBairro(entregador.getBairro());
		entregador.setHoraRegistro(entregadorOptional.get().getHoraRegistro());

		return ResponseEntity.status(HttpStatus.CREATED).body(entregadorRepository.save(entregador));
	}

	@Transactional
	public ResponseEntity<Object> deletarEntregador(Long id) {
		Optional<Entregador> entregadorOptional = entregadorRepository.findById(id);

		if (!entregadorOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entregador não encontrado");
		}
		entregadorRepository.deleteById(entregadorOptional.get().getId());
		return ResponseEntity.status(HttpStatus.OK).body("Entregador removido com sucesso");
	}

	public boolean cpfCadastrado(String cpf) {
		return entregadorRepository.existsByCpf(cpf);
	}

}
