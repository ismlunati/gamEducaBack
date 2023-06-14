package com.gameduca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Alumnos_Retos")
public class AlumnoReto extends BaseEntity {
	
    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoAlumnoReto estado;

    @JsonBackReference(value="alumno-alumnoreto")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @JsonBackReference(value="reto-alumnoreto")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reto_id")
    private Reto reto;

	public EstadoAlumnoReto getEstado() {
		return estado;
	}

	public void setEstado(EstadoAlumnoReto estado) {
		this.estado = estado;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Reto getReto() {
		return reto;
	}

	public void setReto(Reto reto) {
		this.reto = reto;
	}

}
