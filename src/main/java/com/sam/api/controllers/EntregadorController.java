package com.sam.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.api.dtos.EntregadorDto;
import com.sam.api.entities.Entregador;
import com.sam.api.services.EntregadorService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/entregadores")
public class EntregadorController {

	private EntregadorService entregadorService;

	@GetMapping
	public List<Entregador> listarTodos() {
		return entregadorService.listarTodos();

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> encontrarPorId(@PathVariable(value = "id") Long id) {
		return entregadorService.encontrarPorId(id);
	}

	@PostMapping("/cadastrar-entregador")
	public ResponseEntity<Object> salvarEntregador(@RequestBody @Valid EntregadorDto entregadorDto) {
		var entregador = new Entregador();
		BeanUtils.copyProperties(entregadorDto, entregador);
		return entregadorService.salvarEntregador(entregador);
	}

	@PutMapping("/atualizar-entregador/{id}")
	public ResponseEntity<Object> atualizarEntregador(@PathVariable(value = "id") Long id,
			@RequestBody @Valid EntregadorDto entregadorDto) {
		var entregador = new Entregador();
		BeanUtils.copyProperties(entregadorDto, entregador);
		return entregadorService.atualizarEntregador(entregador, id);
	}

	@DeleteMapping("/deletar-entregador/{id}")
	public ResponseEntity<Object> deletarEntregador(@PathVariable(value = "id") Long id) {
		return entregadorService.deletarEntregador(id);
	}
}
