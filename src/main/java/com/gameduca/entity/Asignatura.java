package com.gameduca.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="asignatura")
public class Asignatura extends BaseEntity {
	
	@Column(name="NOMBRE")
	@NotEmpty(message="El nombre no puede estar vacío") 
	String nombre;
	
	@Column(name="DESCRIPCION")
	@NotEmpty(message="La descripcion no puede estar vacia") 
	String descripcion;
	
	@Column(name="CURSO")
	@NotEmpty(message="El curso no puede estar vacío") 
	String curso;
	
	@Column(name="CODIGO")
	String codigo;
	
	@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "PROFESOR_ID")
    private Profesor profesor;

    @JsonManagedReference
    @OneToMany(mappedBy = "asignatura")
    private List<AlumnoAsignatura> alumnoAsignaturas;
	
	public Asignatura(){
		
	}

	public Asignatura(String nombre, String descripcion, String curso, String codigo) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.curso = curso;
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public List<AlumnoAsignatura> getAlumnoAsignaturas() {
		return alumnoAsignaturas;
	}

	public void setAlumnoAsignaturas(List<AlumnoAsignatura> alumnoAsignaturas) {
		this.alumnoAsignaturas = alumnoAsignaturas;
	}
	
}
