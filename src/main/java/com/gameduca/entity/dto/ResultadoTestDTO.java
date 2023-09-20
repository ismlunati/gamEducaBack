package com.gameduca.entity.dto;

import java.util.List;


public class ResultadoTestDTO {
	
	private Integer numeroPreguntasTotales;
	private Integer numeroPreguntasAcertadas;
	private Integer puntuacion;
	private List<PreguntaRespuestaSeleccionadaCorrectaDTO> preguntasRespuestas;
	
	public Integer getNumeroPreguntasTotales() {
		return numeroPreguntasTotales;
	}
	public void setNumeroPreguntasTotales(Integer numeroPreguntasTotales) {
		this.numeroPreguntasTotales = numeroPreguntasTotales;
	}
	public Integer getNumeroPreguntasAcertadas() {
		return numeroPreguntasAcertadas;
	}
	public void setNumeroPreguntasAcertadas(Integer numeroPreguntasAcertadas) {
		this.numeroPreguntasAcertadas = numeroPreguntasAcertadas;
	}
	public Integer getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}
	public List<PreguntaRespuestaSeleccionadaCorrectaDTO> getPreguntasRespuestas() {
		return preguntasRespuestas;
	}
	public void setPreguntasRespuestas(List<PreguntaRespuestaSeleccionadaCorrectaDTO> preguntasRespuestas) {
		this.preguntasRespuestas = preguntasRespuestas;
	}
}
