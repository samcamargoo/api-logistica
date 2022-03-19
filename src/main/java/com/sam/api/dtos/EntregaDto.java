package com.sam.api.dtos;

import com.sam.api.entities.Entrega;

public class EntregaDto {

	private ClienteDto cliente;
	private DestinatarioDto destinatario;
	private EntregadorResponsavelDto entregador;

	public EntregaDto(ClienteDto cliente, DestinatarioDto destinatario, EntregadorResponsavelDto entregador) {
		super();
		this.cliente = cliente;
		this.destinatario = destinatario;
		this.entregador = entregador;
	}

	public EntregaDto(Entrega entrega) {
		cliente = new ClienteDto(entrega.getCliente());
		destinatario = new DestinatarioDto(entrega.getDestinatario());
		entregador = new EntregadorResponsavelDto(entrega.getEntregador());
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public DestinatarioDto getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(DestinatarioDto destinatario) {
		this.destinatario = destinatario;
	}

	public EntregadorResponsavelDto getEntregador() {
		return entregador;
	}

	public void setEntregador(EntregadorResponsavelDto entregador) {
		this.entregador = entregador;
	}

}
