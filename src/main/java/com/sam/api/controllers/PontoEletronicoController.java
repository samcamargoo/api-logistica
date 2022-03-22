package com.sam.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.api.services.PontoEletronicoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/ponto-entregadores")
public class PontoEletronicoController {

	private PontoEletronicoService pontoEntregadorService;
	
	
	@PostMapping("/{cpf}")
	public ResponseEntity<Object> baterPonto(@PathVariable (value = "cpf") String cpf) {
		return pontoEntregadorService.baterPonto(cpf);
	}
	
}
