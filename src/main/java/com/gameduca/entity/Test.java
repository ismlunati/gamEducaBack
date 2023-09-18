package com.gameduca.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "test")
public class Test extends BaseEntity {

    @Column(name="NOMBRE")
    @NotEmpty(message="El nombre no puede estar vacío") 
    private String nombre;
    
    @Column(name="DESCRIPCION")
    private String descripcion;
    
    @Column(name="NUMERO_PREGUNTAS")
    @NotNull(message="El numero de preguntas no puede estar vacío") 
    private Integer numeroPreguntas;
    
    @Column(name="LISTA_TEMAS")
    private String listaTemas;
    
    @Column(name="VISIBLE")
    private boolean visible;
    
    @Column(name="PREGUNTAS_ELEGIBLES")
    private boolean preguntasElegibles;
    
    @Column(name="FECHA_INICIO")
    private Date fechaInicio;
    
    @Column(name="FECHA_FIN")
    private Date fechaFin;
    
    @JsonIgnore
    @OneToMany(mappedBy = "test")
    private List<TestPreguntas> testPreguntas = new ArrayList<>();
    
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ASIGNATURA_ID")
    private Asignatura asignatura;
    
    public Test() {}

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

	public Integer getNumeroPreguntas() {
		return numeroPreguntas;
	}

	public void setNumeroPreguntas(Integer numeroPreguntas) {
		this.numeroPreguntas = numeroPreguntas;
	}

	public List<TestPreguntas> getTestPreguntas() {
		return testPreguntas;
	}

	public void setTestPreguntas(List<TestPreguntas> testPreguntas) {
		this.testPreguntas = testPreguntas;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getListaTemas() {
		return listaTemas;
	}

	public void setListaTemas(String listaTemas) {
		this.listaTemas = listaTemas;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isPreguntasElegibles() {
		return preguntasElegibles;
	}

	public void setPreguntasElegibles(boolean preguntasElegibles) {
		this.preguntasElegibles = preguntasElegibles;
	}
    
}

