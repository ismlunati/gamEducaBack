package com.gameduca.entity.dto;

import java.util.Date;

public class RetoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer puntosOtorgados;
    private boolean temporal;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean automatico;
    private LogroDTO logro; // Referenciamos al Logro solo por su ID
//    private AsignaturaDTO asignatura; // Referenciamos a la Asignatura solo por su ID
//    private List<AlumnoRetoDTO> alumnoRetosIds; // Referenciamos a los AlumnoRetos solo por sus IDs
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
	public Integer getPuntosOtorgados() {
		return puntosOtorgados;
	}
	public void setPuntosOtorgados(Integer puntosOtorgados) {
		this.puntosOtorgados = puntosOtorgados;
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
	public boolean isAutomatico() {
		return automatico;
	}
	public void setAutomatico(boolean automatico) {
		this.automatico = automatico;
	}
	public LogroDTO getLogro() {
		return logro;
	}
	public void setLogro(LogroDTO logro) {
		this.logro = logro;
	}



}

