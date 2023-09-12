package com.gameduca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_PREGUNTAS")
public class TestPreguntas extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "TEST_ID")
    private Test test;
    
    @ManyToOne
    @JoinColumn(name = "PREGUNTA_ID")
    private Pregunta pregunta;
    
    @Column(name="ID_RESPUESTA_ALUMNO")
    private Long idRespuestaAlumno;
    
    @Column(name="RESPONDIDA")
    private boolean respondida;
    
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

	public Long getIdRespuestaAlumno() {
		return idRespuestaAlumno;
	}

	public void setIdRespuestaAlumno(Long idRespuestaAlumno) {
		this.idRespuestaAlumno = idRespuestaAlumno;
	}

	public boolean isRespondida() {
		return respondida;
	}

	public void setRespondida(boolean respondida) {
		this.respondida = respondida;
	}
    
    
}

