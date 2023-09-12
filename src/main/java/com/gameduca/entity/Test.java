package com.gameduca.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "test")
public class Test extends BaseEntity {

    @Column(name="NOMBRE")
    @NotEmpty(message="El nombre no puede estar vacío") 
    private String nombre;
    
    @Column(name="DESCRIPCION")
    private String descripcion;
    
    @Column(name="NUMERO_PREGUNTAS")
    @NotEmpty(message="El numero de preguntas no puede estar vacío") 
    private Integer numeroPreguntas;
    
    @Column(name="AUTOMATICO")
    private boolean automatico;
    
    @Column(name="FECHA_INICIO")
    private Date fechaInicio;
    
    @Column(name="FECHA_FIN")
    private Date fechaFin;
    
    @OneToMany(mappedBy = "test")
    private List<TestPreguntas> testPreguntas = new ArrayList<>();
    
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

	public boolean isAutomatico() {
		return automatico;
	}

	public void setAutomatico(boolean automatico) {
		this.automatico = automatico;
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
    
}

