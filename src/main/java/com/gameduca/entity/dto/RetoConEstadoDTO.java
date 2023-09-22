package com.gameduca.entity.dto;

import com.gameduca.entity.EstadoAlumnoReto;
import com.gameduca.entity.Reto;

public class RetoConEstadoDTO {
	
	private Reto reto;
    private EstadoAlumnoReto estado;
    private Long idAlumnoReto;
    
	public Reto getReto() {
		return reto;
	}
	public void setReto(Reto reto) {
		this.reto = reto;
	}
	public EstadoAlumnoReto getEstado() {
		return estado;
	}
	public void setEstado(EstadoAlumnoReto estado) {
		this.estado = estado;
	}
	public Long getIdAlumnoReto() {
		return idAlumnoReto;
	}
	public void setIdAlumnoReto(Long idAlumnoReto) {
		this.idAlumnoReto = idAlumnoReto;
	}

}
