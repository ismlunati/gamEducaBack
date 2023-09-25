package com.gameduca.entity;

import java.util.ArrayList;
import java.util.List;

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
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	@JsonBackReference(value="asignatura-profesor")
    @ManyToOne
    @JoinColumn(name = "PROFESOR_ID")
    private Profesor profesor;
	
    @JsonManagedReference(value="asignatura-artefacto")
    @OneToMany(mappedBy = "asignatura")
    private List<Artefacto> artefactos;
    
    @JsonManagedReference(value="asignatura-tema")
    @OneToMany(mappedBy = "asignatura")
    private List<Tema> temas;

    @JsonManagedReference(value="asignatura-alumnoasignatura")
    @OneToMany(mappedBy = "asignatura")
    private List<AlumnoAsignatura> alumnoAsignaturas;
    
    @OneToMany(mappedBy = "asignatura")
    @JsonManagedReference(value="reto-asignatura")
    private List<Reto> retos;
    
    @OneToMany(mappedBy = "asignatura")
//    @JsonManagedReference(value="reto-asignatura")
    @JsonIgnore
    private List<Logro> logros;
    
//    @JsonManagedReference(value="pregunta-asignatura")
    @OneToMany(mappedBy = "asignatura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas;
    
    @OneToMany(mappedBy = "asignatura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> tests;
	
	public Asignatura(){
		
	}

	public Asignatura(String nombre, String descripcion, String curso, String codigo) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.curso = curso;
		this.codigo = codigo;
	}
	
	

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

	public List<Artefacto> getArtefactos() {
		return artefactos;
	}

	public void setArtefactos(List<Artefacto> artefactos) {
		this.artefactos = artefactos;
	}

	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}

	public List<Reto> getRetos() {
		return retos;
	}

	public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}
	
}
