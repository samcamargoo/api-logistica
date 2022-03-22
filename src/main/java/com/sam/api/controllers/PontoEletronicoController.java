package com.sam.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sam.api.entities.PontoEletronico;
import com.sam.api.services.PontoEletronicoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/ponto-eletronico")
public class PontoEletronicoController {

	private PontoEletronicoService pontoEletronicoService;
	
	@GetMapping
	public List<PontoEletronico> entregadoresDisponiveis() {
		return pontoEletronicoService.obterEntregadoresDisponiveis();
	}
	
	@PostMapping("/{cpf}")
	public ResponseEntity<Object> baterPonto(@PathVariable (value = "cpf") String cpf) {
		return pontoEletronicoService.baterPontoEntrada(cpf);
	}
	
}
