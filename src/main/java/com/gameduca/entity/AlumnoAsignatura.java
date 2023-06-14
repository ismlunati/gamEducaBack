package com.gameduca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="ALUMNO_ASIGNATURA")
public class AlumnoAsignatura extends BaseEntity {

    @Enumerated(EnumType.STRING)
	@Column(name="ESTADO")
    private EstadoAlumnoAsignatura estado;
	
	@Column(name="PUNTOS")
    private Integer puntos;

	@JsonBackReference(value="alumno-alumnoasignatura")
    @ManyToOne
    @JoinColumn(name = "ALUMNO_ID")
    private Alumno alumno;

	@JsonBackReference(value="asignatura-alumnoasignatura")
    @ManyToOne
    @JoinColumn(name = "ASIGNATURA_ID")
    private Asignatura asignatura;

	public EstadoAlumnoAsignatura getEstado() {
		return estado;
	}

	public void setEstado(EstadoAlumnoAsignatura estado) {
		this.estado = estado;
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
	
	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public AlumnoAsignatura(EstadoAlumnoAsignatura estado, Integer puntos, Alumno alumno, Asignatura asignatura) {
		super();
		this.estado = estado;
		this.puntos = puntos;
		this.alumno = alumno;
		this.asignatura = asignatura;
	}
    
	public AlumnoAsignatura(){}
 
}


