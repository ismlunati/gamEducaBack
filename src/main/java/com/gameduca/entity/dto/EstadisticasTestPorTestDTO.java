package com.gameduca.entity.dto;

import java.util.List;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Tema;
import com.gameduca.entity.Test;

public class EstadisticasTestPorTestDTO {
	
	private Test test;
	private List<EstadisticasAlumnosTest> estadisticas;
	
	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public List<EstadisticasAlumnosTest> getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(List<EstadisticasAlumnosTest> estadisticas) {
		this.estadisticas = estadisticas;
	}

	public class EstadisticasAlumnosTest {
		private Alumno alumno;
		private List<Double> puntuacion;
		
		public Alumno getAlumno() {
			return alumno;
		}
		public void setAlumno(Alumno alumno) {
			this.alumno = alumno;
		}
		public List<Double> getPuntuacion() {
			return puntuacion;
		}
		public void setPuntuacion(List<Double> puntuacion) {
			this.puntuacion = puntuacion;
		}
		
	}

}
