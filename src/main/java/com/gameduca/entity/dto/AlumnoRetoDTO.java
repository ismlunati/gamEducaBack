package com.gameduca.entity.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.EstadoAlumnoReto;
import com.gameduca.entity.Reto;

public class AlumnoRetoDTO {

	private Alumno alumno;
	private List<RetoConEstadoDTO> retoConEstado;
	
//	private Reto reto;
//	private Long alumnoRetoId;
//	private EstadoAlumnoReto estado;


	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<RetoConEstadoDTO> getRetoConEstado() {
		return retoConEstado;
	}

	public void setRetoConEstado(List<RetoConEstadoDTO> retoConEstado) {
		this.retoConEstado = retoConEstado;
	}

}
