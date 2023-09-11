package com.gameduca.entity.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.EstadoAlumnoReto;
import com.gameduca.entity.Reto;

public class AlumnoRetoDTO {
//	private Long alumnoId;
//	private String nombreUsuario;
	private Alumno alumno;
	
	private Reto reto;
//	private Long retoId;
//	private String nombre;
//	private String descripcion;
//	private Integer puntosOtorgados;
//	private boolean temporal;
//	private  Date fechaInicio;
//	private Date fechaFin;
//	private boolean automatico;
   
	private Long alumnoRetoId;
	private EstadoAlumnoReto estado;

//	public Long getAlumnoId() {
//		return alumnoId;
//	}
//
//	public void setAlumnoId(Long alumnoId) {
//		this.alumnoId = alumnoId;
//	}
//
//	public String getNombreUsuario() {
//		return nombreUsuario;
//	}
//
//	public void setNombreUsuario(String nombreUsuario) {
//		this.nombreUsuario = nombreUsuario;
//	}
//
//	public Long getRetoId() {
//		return retoId;
//	}
//
//	public void setRetoId(Long retoId) {
//		this.retoId = retoId;
//	}
//
//	public String getNombre() {
//		return nombre;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	public String getDescripcion() {
//		return descripcion;
//	}
//
//	public void setDescripcion(String descripcion) {
//		this.descripcion = descripcion;
//	}
//
//	public Integer getPuntosOtorgados() {
//		return puntosOtorgados;
//	}
//
//	public void setPuntosOtorgados(Integer puntosOtorgados) {
//		this.puntosOtorgados = puntosOtorgados;
//	}
//
//	public boolean isTemporal() {
//		return temporal;
//	}
//
//	public void setTemporal(boolean temporal) {
//		this.temporal = temporal;
//	}
//
//	public Date getFechaInicio() {
//		return fechaInicio;
//	}
//
//	public void setFechaInicio(Date fechaInicio) {
//		this.fechaInicio = fechaInicio;
//	}
//
//	public Date getFechaFin() {
//		return fechaFin;
//	}
//
//	public void setFechaFin(Date fechaFin) {
//		this.fechaFin = fechaFin;
//	}
//
//	public boolean isAutomatico() {
//		return automatico;
//	}
//
//	public void setAutomatico(boolean automatico) {
//		this.automatico = automatico;
//	}

	public EstadoAlumnoReto getEstado() {
		return estado;
	}

	public void setEstado(EstadoAlumnoReto estado) {
		this.estado = estado;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Reto getReto() {
		return reto;
	}

	public void setReto(Reto reto) {
		this.reto = reto;
	}

	public Long getAlumnoRetoId() {
		return alumnoRetoId;
	}

	public void setAlumnoRetoId(Long alumnoRetoId) {
		this.alumnoRetoId = alumnoRetoId;
	}
	
	

}
