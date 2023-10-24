package com.gameduca.entity.dto;

import java.util.List;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Tema;
import com.gameduca.entity.Test;

public class EstadisticasTestPorAlumnosDTO {
	
	private Alumno alumno;
	private List<EstadisticasTestTest> estadisticas;
	
	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<EstadisticasTestTest> getEstadisticas() {
		return estadisticas;
	}

	public void setEstadisticas(List<EstadisticasTestTest> estadisticas) {
		this.estadisticas = estadisticas;
	}

	public class EstadisticasTestTest {
		private Test test;
		private List<Double> puntuacion;
		public Test getTest() {
			return test;
		}
		public void setTest(Test test) {
			this.test = test;
		}
		public List<Double> getPuntuacion() {
			return puntuacion;
		}
		public void setPuntuacion(List<Double> puntuacion) {
			this.puntuacion = puntuacion;
		}		
	}
}

