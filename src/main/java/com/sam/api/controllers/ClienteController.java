package com.sam.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.api.dtos.ClienteDto;
import com.sam.api.entities.Cliente;
import com.sam.api.services.ClienteService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteService clienteService;

	@GetMapping
	public List<Cliente> listarTodos() {
		return clienteService.listarTodos();

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> encontrarPorId(@PathVariable(value = "id") Long id){
		return clienteService.encontrarPorId(id);
	}
	
	@PostMapping("/cadastrar-cliente")
	public ResponseEntity<Object> salvarCliente(@RequestBody @Valid ClienteDto clienteDto){
		var cliente = new Cliente();
		BeanUtils.copyProperties(clienteDto, cliente);
		return clienteService.salvarCliente(cliente);
	}
	
	@PutMapping("/atualizar-cliente/{id}")
	public ResponseEntity<Object> atualizarCliente(@PathVariable(value = "id") Long id, @RequestBody @Valid ClienteDto clienteDto) {
		var cliente = new Cliente();
		BeanUtils.copyProperties(clienteDto, cliente);
		return clienteService.atualizarCliente(cliente, id);
	}
}
