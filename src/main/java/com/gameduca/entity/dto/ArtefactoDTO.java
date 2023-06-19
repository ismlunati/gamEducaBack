package com.gameduca.entity.dto;

import java.util.Date;
import java.util.List;

import com.gameduca.entity.EstadoArtefacto;

public class ArtefactoDTO {
    
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer costePuntos;
    private EstadoArtefacto estado;
    private boolean temporal;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean repetible;
//    private AsignaturaDTO asignatura;
//    private List<CompraDTO> compras;
    private ArtefactoLogroDTO artefactoLogros;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getCostePuntos() {
		return costePuntos;
	}
	public void setCostePuntos(Integer costePuntos) {
		this.costePuntos = costePuntos;
	}
	public EstadoArtefacto getEstado() {
		return estado;
	}
	public void setEstado(EstadoArtefacto estado) {
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
	public boolean isRepetible() {
		return repetible;
	}
	public void setRepetible(boolean repetible) {
		this.repetible = repetible;
	}
	public ArtefactoLogroDTO getArtefactoLogros() {
		return artefactoLogros;
	}
	public void setArtefactoLogros(ArtefactoLogroDTO artefactoLogros) {
		this.artefactoLogros = artefactoLogros;
	}    
    
}


