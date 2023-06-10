package com.gameduca.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "alumno")
public class Alumno extends BaseEntity{
    
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "NOMBREUSUARIO", referencedColumnName = "NOMBREUSUARIO")
	private Usuario usuario;
    
    @JsonManagedReference(value="alumno-alumnoasignatura")
    @OneToMany(mappedBy = "alumno")
    private List<AlumnoAsignatura> alumnoAsignaturas;
    
    @JsonManagedReference(value="alumno-alumnoreto")
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlumnoReto> alumnoRetos = new ArrayList<>();
    
    @JsonManagedReference(value="compra-alumno")
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras = new ArrayList<>();


	public List<AlumnoAsignatura> getAlumnoAsignaturas() {
		return alumnoAsignaturas;
	}

	public void setAlumnoAsignaturas(List<AlumnoAsignatura> alumnoAsignaturas) {
		this.alumnoAsignaturas = alumnoAsignaturas;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<AlumnoReto> getAlumnoRetos() {
		return alumnoRetos;
	}

	public void setAlumnoRetos(List<AlumnoReto> alumnoRetos) {
		this.alumnoRetos = alumnoRetos;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public Alumno() {}

}
