package com.gameduca.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "artefacto")
public class Artefacto extends BaseEntity{
	
    @NotNull
    @Column(name="NOMBRE")
    private String nombre;
    
    @NotNull
    @Column(name="DESCRIPCION")
    private String descripcion;
    
    @NotNull
    @Column(name="COSTEPUNTOS")
    private Integer costePuntos;
    
    @NotNull
    @Column(name="ESTADO")
    private String estado;
    
    @NotNull
    @Column(name="TEMPORAL")
    private boolean temporal;
    
    @Column(name="FECHAINICIO")
    private Date fechaInicio;
    
    @Column(name="FECHAFIN")
    private Date fechaFin;
    
    @JsonBackReference(value="asignatura-artefacto")
    @ManyToOne
    @JoinColumn(name = "ASIGNATURA_ID")
    private Asignatura asignatura;
    
    @ManyToOne
    @JoinColumn(name = "LOGRO_ID")
    @JsonBackReference(value="logro-artefacto")
    private Logro logro;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCostePuntos() {
		return costePuntos;
	}

	public void setCostePuntos(Integer costePuntos) {
		this.costePuntos = costePuntos;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isTemporal() {
		return temporal;
	}

	public void setTemporal(boolean temporal) {
		this.temporal = temporal;
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

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Logro getLogro() {
		return logro;
	}

	public void setLogro(Logro logro) {
		this.logro = logro;
	}

	public Artefacto(@NotNull String nombre, @NotNull String descripcion, @NotNull Integer costePuntos, @NotNull String estado,
			@NotNull boolean temporal, Date fechaInicio, Date fechaFin, Asignatura asignatura) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costePuntos = costePuntos;
		this.estado = estado;
		this.temporal = temporal;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.asignatura = asignatura;
	}
    
    public Artefacto() {}
    
    
	

}
