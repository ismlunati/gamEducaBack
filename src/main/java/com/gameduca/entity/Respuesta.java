package com.gameduca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "respuesta")
public class Respuesta extends BaseEntity {

    @Column(name="TEXTO")
    @NotEmpty(message="El texto de la respuesta no puede estar vacío") 
    private String texto;
    
    @Column(name="ES_CORRECTA")
    private Boolean esCorrecta;
    
    @ManyToOne
    @JoinColumn(name = "PREGUNTA_ID")
    private Pregunta pregunta;
    
    public Respuesta() {}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Boolean getEsCorrecta() {
		return esCorrecta;
	}

	public void setEsCorrecta(Boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}
    
    
    
}

