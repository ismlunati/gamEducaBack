package com.gameduca.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "PUNTUACION_TEST")
public class PuntuacionTest extends BaseEntity {
	
    @Column(name = "PUNTUACION")
    private Double puntuacion;
    
    @OneToMany(mappedBy = "puntuacionTest")
    private List<TestPreguntas> testPreguntasList = new ArrayList<>();

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public List<TestPreguntas> getTestPreguntasList() {
		return testPreguntasList;
	}

	public void setTestPreguntasList(List<TestPreguntas> testPreguntasList) {
		this.testPreguntasList = testPreguntasList;
	}
    
}
