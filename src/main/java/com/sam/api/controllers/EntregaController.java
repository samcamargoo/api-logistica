package com.sam.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.api.dtos.EntregaDto;
import com.sam.api.entities.Entrega;
import com.sam.api.services.EntregaService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/entregas")
@AllArgsConstructor
public class EntregaController {

	private EntregaService entregaService;

	@GetMapping
	public List<EntregaDto> listarTodasEntregas() {
		return entregaService.listarTodasEntregas();
	}

	@PostMapping
	public ResponseEntity<Object> criarEntrega(@RequestBody @Valid Entrega entrega) {
		
		
		return entregaService.criarEntrega(entrega);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletarEntrega(@PathVariable (value = "id") Long id) {
		return entregaService.deletarEntrega(id);
	}
}
