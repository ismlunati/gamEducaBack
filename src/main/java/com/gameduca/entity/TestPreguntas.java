package com.gameduca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TEST_PREGUNTAS")
public class TestPreguntas extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "TEST_ID")
    private Test test;
    
    @ManyToOne
    @JoinColumn(name = "PREGUNTA_ID")
    private Pregunta pregunta;
    
    @Column(name="ID_RESPUESTA_SELECCIONADA")
    private Long idRespuestaSeleccionada;
    
    @Column(name="ID_ALUMNO_QUE_RESPONDE")
    private Long idAlumnoQueResponde;
    
    @Column(name="RESPONDIDA")
    private boolean respondida;
    
    @Column(name="NUMERO_INTENTO")
    private Integer numeroIntento;
    
    public TestPreguntas() {}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public boolean isRespondida() {
		return respondida;
	}

	public void setRespondida(boolean respondida) {
		this.respondida = respondida;
	}

	public Long getIdRespuestaSeleccionada() {
		return idRespuestaSeleccionada;
	}

	public void setIdRespuestaSeleccionada(Long idRespuestaSeleccionada) {
		this.idRespuestaSeleccionada = idRespuestaSeleccionada;
	}

	public Long getIdAlumnoQueResponde() {
		return idAlumnoQueResponde;
	}

	public void setIdAlumnoQueResponde(Long idAlumnoQueResponde) {
		this.idAlumnoQueResponde = idAlumnoQueResponde;
	}

	public Integer getNumeroIntento() {
		return numeroIntento;
	}

	public void setNumeroIntento(Integer numeroIntento) {
		this.numeroIntento = numeroIntento;
	}
	
}

