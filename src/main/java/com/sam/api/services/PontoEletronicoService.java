package com.sam.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sam.api.entities.Entregador;
import com.sam.api.entities.PontoEletronico;
import com.sam.api.repositories.PontoEletronicoRepository;
import com.sam.api.repositories.EntregadorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PontoEletronicoService {

	private EntregadorRepository entregadorRepository;
	private PontoEletronicoRepository pontoEletronicoRepository;

	public List<PontoEletronico> obterEntregadoresDisponiveis() {
		return pontoEletronicoRepository.findAll();

	}
	
	public ResponseEntity<Object> baterPontoEntrada(String cpf) {
		var pontoEletronicoEntregador = new PontoEletronico();
		Entregador entregador = entregadorRepository.findByCpf(cpf);
		
		if(!entregadorRepository.existsByCpf(cpf) ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cpf não encontrado em nossa base de dados");
		}
		/*TODO
		 * Verificar se o entregador já bateu ponto no mesmo dia em horario anterior,
		 * Adicionar hora de entrada
		 * 
		 * */
		
		pontoEletronicoEntregador.setNome(entregador.getNome());
		pontoEletronicoEntregador.setId(entregador.getId());
		pontoEletronicoEntregador.setCpf(entregador.getCpf());
		pontoEletronicoEntregador.setDisponivel(true);
		pontoEletronicoRepository.save(pontoEletronicoEntregador);
		return ResponseEntity.status(HttpStatus.CREATED).body(entregador.getNome() + " bateu ponto");
	}
	
	public ResponseEntity<Object> baterPontoSaida(String cpf) {
		return null;
		
		/*TODO
		 * Verificar a hora de saída (estipular um horario de saída),
		 * Verificar se o entregador bateu ponto para entrar antes de sair,
		 * Verificar se o entregador bateu ponto para sair no mesmo dia em horario anterior,
		 * Pensar em mais verificações
		 * */
	}
}