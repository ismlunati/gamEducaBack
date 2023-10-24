package com.gameduca.entity.dto;

import java.util.List;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Tema;

public class EstadisticasPreguntasPorAlumnosDTO {
	
	private Alumno alumno;
	private List<EstadisticasTemasPreguntas> estadisticas;
	
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<EstadisticasTemasPreguntas> getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(List<EstadisticasTemasPreguntas> estadisticas) {
		this.estadisticas = estadisticas;
	}

	public class EstadisticasTemasPreguntas {
		private Tema tema;
		private Integer numeroDePreguntas;
		
		public Integer getNumeroDePreguntas() {
			return numeroDePreguntas;
		}
		public void setNumeroDePreguntas(Integer numeroDePreguntas) {
			this.numeroDePreguntas = numeroDePreguntas;
		}
		public Tema getTema() {
			return tema;
		}
		public void setTema(Tema tema) {
			this.tema = tema;
		}
		
	}
}

