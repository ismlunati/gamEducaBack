package com.gameduca.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "logro")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Logro extends BaseEntity {

    @Column(name = "NOMBRE")
    @NotEmpty(message = "El nombre no puede estar vacío")
    String nombre;

    @Column(name = "DESCRIPCION")
    @NotEmpty(message = "La descripción no puede estar vacía")
    String descripcion;
    
    @Column(name="IMAGEN")
    String imagen;
    
    @OneToMany(mappedBy = "logro")
    @JsonManagedReference(value="reto-logro")
    private List<Reto> retos;
    
    @JsonManagedReference(value="logro-artefactologro")
//    @OneToMany(mappedBy = "logro", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToOne(mappedBy = "logro", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArtefactoLogro artefactoLogros;
    

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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public List<Reto> getRetos() {
		return retos;
	}

	public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}
	
	public ArtefactoLogro getArtefactoLogros() {
		return artefactoLogros;
	}

	public void setArtefactoLogros(ArtefactoLogro artefactoLogros) {
		this.artefactoLogros = artefactoLogros;
	}

	public Logro(@NotEmpty(message = "El nombre no puede estar vacío") String nombre,
			@NotEmpty(message = "La descripción no puede estar vacía") String descripcion, List<Reto> retos) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.retos = retos;
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
