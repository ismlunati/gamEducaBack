package com.gameduca.entity.dto;

import java.util.List;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Tema;

public class EstadisticasPreguntasPorTemasDTO {
	
	private Tema tema;
	private List<EstadisticasAlumnosPreguntas> estadisticas;
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<EstadisticasAlumnosPreguntas> getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(List<EstadisticasAlumnosPreguntas> estadisticas) {
		this.estadisticas = estadisticas;
	}




	public class EstadisticasAlumnosPreguntas {
		private Alumno alumno;
		private Integer numeroDePreguntas;
		
		public Alumno getAlumno() {
			return alumno;
		}
		public void setAlumno(Alumno alumno) {
			this.alumno = alumno;
		}
		public Integer getNumeroDePreguntas() {
			return numeroDePreguntas;
		}
		public void setNumeroDePreguntas(Integer numeroDePreguntas) {
			this.numeroDePreguntas = numeroDePreguntas;
		}
		
	}

}
