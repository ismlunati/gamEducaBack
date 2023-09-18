package com.gameduca.entity.dto;

import java.util.List;

import com.gameduca.entity.Pregunta;
import com.gameduca.entity.TestPreguntas;

public class ResultadoTestDTO {
	
	private Integer numeroPreguntasTotales;
	private Integer numeroPreguntasAcertadas;
	private Integer puntuacion;
	private List<Pregunta> preguntasRespondidas;
	private List<TestPreguntas> respuestas;

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
	public List<Pregunta> getPreguntasRespondidas() {
		return preguntasRespondidas;
	}
	public void setPreguntasRespondidas(List<Pregunta> preguntasRespondidas) {
		this.preguntasRespondidas = preguntasRespondidas;
	}
	public List<TestPreguntas> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(List<TestPreguntas> respuestas) {
		this.respuestas = respuestas;
	}
}
