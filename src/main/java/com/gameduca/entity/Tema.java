package com.gameduca.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tema")
public class Tema extends BaseEntity{
	
	@Column(name="NOMBRE")
	@NotEmpty(message="El nombre no puede estar vacío") 
	String nombre;
	
	@Column(name="DESCRIPCION")
	@NotEmpty(message="La descripcion no puede estar vacia") 
	String descripcion;
	
	@JsonBackReference(value="asignatura-tema")
    @ManyToOne
    @JoinColumn(name = "ASIGNATURA_ID")
    private Asignatura asignatura;
	
//    @JsonManagedReference(value="pregunta-tema")
	@JsonIgnore
	@OneToMany(mappedBy = "tema", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Pregunta> preguntas;
	 
	 
    
	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
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

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Tema(@NotEmpty(message = "El nombre no puede estar vacío") String nombre,
			@NotEmpty(message = "La descripcion no puede estar vacia") String descripcion, Asignatura asignatura) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.asignatura = asignatura;
	}
	
	public Tema() {}
	

}
