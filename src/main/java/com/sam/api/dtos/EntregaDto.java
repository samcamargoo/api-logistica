package com.sam.api.dtos;

import org.springframework.hateoas.RepresentationModel;

import com.sam.api.entities.Entrega;
import com.sam.api.enums.StatusEntrega;

import lombok.Data;

@Data
public class EntregaDto extends RepresentationModel<EntregaDto>{

	private ClienteDto cliente;
	private DestinatarioDto destinatario;
	private EntregadorResponsavelDto entregador;
	private StatusEntrega status;

	public EntregaDto(ClienteDto cliente, DestinatarioDto destinatario, EntregadorResponsavelDto entregador, StatusEntrega status) {
		super();
		this.cliente = cliente;
		this.destinatario = destinatario;
		this.entregador = entregador;
		this.status = status;
	}

	public EntregaDto(Entrega entrega) {
		cliente = new ClienteDto(entrega.getCliente());
		destinatario = new DestinatarioDto(entrega.getDestinatario());
		entregador = new EntregadorResponsavelDto(entrega.getEntregador());
		status = entrega.getStatus();
	}

}
