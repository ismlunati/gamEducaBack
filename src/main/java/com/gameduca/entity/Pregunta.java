package com.gameduca.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pregunta")
public class Pregunta extends BaseEntity {

    @Column(name="ENUNCIADO")
    @NotEmpty(message="El enunciado no puede estar vacío") 
    private String enunciado;
    
    @ManyToOne
    @JoinColumn(name = "ALUMNO_ID")
    private Alumno alumno;
    
    @ManyToOne
    @JoinColumn(name = "ASIGNATURA_ID")
    private Asignatura asignatura;
    
    @ManyToOne
    @JoinColumn(name = "TEMA_ID")
    private Tema tema;
    
    @OneToMany(mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Respuesta> respuestas = new ArrayList<>();
    
    @OneToMany(mappedBy = "pregunta")
    private List<TestPreguntas> testPreguntas = new ArrayList<>();
    
    public Pregunta() {}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public List<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}

	public List<TestPreguntas> getTestPreguntas() {
		return testPreguntas;
	}

	public void setTestPreguntas(List<TestPreguntas> testPreguntas) {
		this.testPreguntas = testPreguntas;
	}
    
    
}

