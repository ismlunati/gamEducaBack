package com.gameduca.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "reto")
public class Reto extends BaseEntity {

    @Column(name = "NOMBRE")
    @NotEmpty(message = "El nombre no puede estar vacío")
    String nombre;

    @Column(name = "DESCRIPCION")
    @NotEmpty(message = "La descripción no puede estar vacía")
    String descripcion;

    @Column(name = "PUNTOS_OTORGADOS")
    @NotEmpty(message = "Un reto tiene que otogar puntos")
    Integer puntosOtorgados;
    
    @NotNull
    @Column(name="TEMPORAL")
    private boolean temporal;
    
    @Column(name="FECHAINICIO")
    private Date fechaInicio;
    
    @Column(name="FECHAFIN")
    private Date fechaFin;

    @ManyToOne
    @JoinColumn(name = "LOGRO_ID")
    @JsonBackReference(value="reto-logro")
    private Logro logro;

    @ManyToOne
    @JoinColumn(name = "TEMA_ID")
    @JsonBackReference(value="reto-tema")
    private Tema tema;
    
    @ManyToOne
    @JoinColumn(name = "ASIGNATURA_ID")
    @JsonBackReference(value="reto-asignatura")
    private Asignatura asignatura;
    
    @OneToMany(mappedBy = "reto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlumnoReto> alumnoRetos = new ArrayList<>();

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

	public Logro getLogro() {
		return logro;
	}

	public void setLogro(Logro logro) {
		this.logro = logro;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public List<AlumnoReto> getAlumnoRetos() {
		return alumnoRetos;
	}

	public void setAlumnoRetos(List<AlumnoReto> alumnoRetos) {
		this.alumnoRetos = alumnoRetos;
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

	public Reto(@NotEmpty(message = "El nombre no puede estar vacío") String nombre,
			@NotEmpty(message = "La descripción no puede estar vacía") String descripcion,
			@NotEmpty(message = "Un reto tiene que otogar puntos") Integer puntosOtorgados, Logro logro, Tema tema) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.puntosOtorgados = puntosOtorgados;
		this.logro = logro;
		this.tema = tema;
	}

	public Reto() {}

}
