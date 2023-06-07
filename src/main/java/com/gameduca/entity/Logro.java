package com.gameduca.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "logro")
public class Logro extends BaseEntity {

    @Column(name = "NOMBRE")
    @NotEmpty(message = "El nombre no puede estar vacío")
    String nombre;

    @Column(name = "DESCRIPCION")
    @NotEmpty(message = "La descripción no puede estar vacía")
    String descripcion;
    
    @OneToMany(mappedBy = "logro")
    @JsonManagedReference(value="reto-logro")
    private List<Reto> retos;
    
    @ManyToMany
    @JoinTable(name = "logros_artefactos",
        joinColumns = @JoinColumn(name = "logro_id"),
        inverseJoinColumns = @JoinColumn(name = "artefacto_id")
    )
    private List<Artefacto> artefactos = new ArrayList<>();
    
    

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

	public List<Reto> getRetos() {
		return retos;
	}

	public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}

	public List<Artefacto> getArtefactos() {
		return artefactos;
	}

	public void setArtefactos(List<Artefacto> artefactos) {
		this.artefactos = artefactos;
	}

	public Logro(@NotEmpty(message = "El nombre no puede estar vacío") String nombre,
			@NotEmpty(message = "La descripción no puede estar vacía") String descripcion, List<Reto> retos,
			List<Artefacto> artefactos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.retos = retos;
		this.artefactos = artefactos;
	}

	public Logro(@NotEmpty(message = "El nombre no puede estar vacío") String nombre,
			@NotEmpty(message = "La descripción no puede estar vacía") String descripcion, String beneficio,
			@NotEmpty(message = "El estado no puede estar vacío") boolean estado, List<Reto> retos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.retos = retos;
	}
    
	public Logro() {}

}
