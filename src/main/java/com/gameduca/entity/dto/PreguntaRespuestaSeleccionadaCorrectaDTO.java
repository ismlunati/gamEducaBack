package com.gameduca.entity.dto;

import java.util.List;

import com.gameduca.entity.Pregunta;
import com.gameduca.entity.Respuesta;

public class PreguntaRespuestaSeleccionadaCorrectaDTO {
	private Pregunta pregunta;
	private List<Respuesta> respuestas;
	public Pregunta getPregunta() {
		return pregunta;
	}
	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
	public List<Respuesta> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

}
