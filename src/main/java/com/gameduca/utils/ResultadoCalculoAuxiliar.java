package com.gameduca.utils;

import java.util.List;

import com.gameduca.entity.dto.PreguntaRespuestaSeleccionadaCorrectaDTO;

public class ResultadoCalculoAuxiliar {
    private List<PreguntaRespuestaSeleccionadaCorrectaDTO> preguntasRespuestas;
    private Integer preguntasAcertadas;
    private Double resultado;
    
    
	public ResultadoCalculoAuxiliar(List<PreguntaRespuestaSeleccionadaCorrectaDTO> preguntasRespuestas,
			Integer preguntasAcertadas, Double resultado) {
		super();
		this.preguntasRespuestas = preguntasRespuestas;
		this.preguntasAcertadas = preguntasAcertadas;
		this.resultado = resultado;
	}
	public List<PreguntaRespuestaSeleccionadaCorrectaDTO> getPreguntasRespuestas() {
		return preguntasRespuestas;
	}
	public void setPreguntasRespuestas(List<PreguntaRespuestaSeleccionadaCorrectaDTO> preguntasRespuestas) {
		this.preguntasRespuestas = preguntasRespuestas;
	}
	public Integer getPreguntasAcertadas() {
		return preguntasAcertadas;
	}
	public void setPreguntasAcertadas(Integer preguntasAcertadas) {
		this.preguntasAcertadas = preguntasAcertadas;
	}
	public Double getResultado() {
		return resultado;
	}
	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}
    
}
