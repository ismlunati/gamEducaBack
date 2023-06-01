package com.gameduca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Asignatura extends BaseEntity {
	
	@Column(name="nombre")
	@NotEmpty(message="El nombre no puede estar vacío") 
	String nombre;
	
	@Column(name="descripcion")
	@NotEmpty(message="La descripcion no puede estar vacia") 
	String descripcion;
	
	@Column(name="curso")
	@NotEmpty(message="El curso no puede estar vacío") 
	String curso;
	
	public Asignatura(){
		
	}

	public Asignatura(String nombre, String descripcion, String curso) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.curso = curso;
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
	
	

}
