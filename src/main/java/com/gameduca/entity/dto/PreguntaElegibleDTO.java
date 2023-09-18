package com.gameduca.entity.dto;

public class PreguntaElegibleDTO {
	private Long id;
	private String enunciado;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}
	public PreguntaElegibleDTO(Long id, String enunciado) {
		super();
		this.id = id;
		this.enunciado = enunciado;
	}

	
}
