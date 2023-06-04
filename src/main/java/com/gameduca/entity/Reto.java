package com.gameduca.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
