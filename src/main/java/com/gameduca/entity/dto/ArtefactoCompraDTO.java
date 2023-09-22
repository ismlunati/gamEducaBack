package com.gameduca.entity.dto;

import com.gameduca.entity.Artefacto;
import com.gameduca.entity.EstadoCompra;

public class ArtefactoCompraDTO {
	
	private Artefacto artefacto;
	private EstadoCompra estadoDeLaCompra;
	private Long idCompra;
	public Artefacto getArtefacto() {
		return artefacto;
	}
	public void setArtefacto(Artefacto artefacto) {
		this.artefacto = artefacto;
	}
	public EstadoCompra getEstadoDeLaCompra() {
		return estadoDeLaCompra;
	}
	public void setEstadoDeLaCompra(EstadoCompra estadoDeLaCompra) {
		this.estadoDeLaCompra = estadoDeLaCompra;
	}
	public Long getIdCompra() {
		return idCompra;
	}
	public void setIdCompra(Long idCompra) {
		this.idCompra = idCompra;
	}
	
	

}
