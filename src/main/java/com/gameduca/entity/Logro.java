package com.gameduca.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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

    @Column(name = "BENEFICIO")
    String beneficio;

    @Column(name = "ESTADO")
    @NotEmpty(message = "El estado no puede estar vacío")
    boolean estado;
    
    @OneToMany(mappedBy = "logro")
    @JsonManagedReference(value="reto-logro")
    private List<Reto> retos;
    
    @OneToMany(mappedBy = "logro")
    @JsonManagedReference(value="logro-artefacto")
    private List<Artefacto> artefactosDesbloqueados;

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

	public String getBeneficio() {
		return beneficio;
	}

	public void setBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Reto> getRetos() {
		return retos;
	}

	public void setRetos(List<Reto> retos) {
		this.retos = retos;
	}

	public List<Artefacto> getArtefactosDesbloqueados() {
		return artefactosDesbloqueados;
	}

	public void setArtefactosDesbloqueados(List<Artefacto> artefactosDesbloqueados) {
		this.artefactosDesbloqueados = artefactosDesbloqueados;
	}

	public Logro(@NotEmpty(message = "El nombre no puede estar vacío") String nombre,
			@NotEmpty(message = "La descripción no puede estar vacía") String descripcion, String beneficio,
			@NotEmpty(message = "El estado no puede estar vacío") boolean estado, List<Reto> retos,
			List<Artefacto> artefactosDesbloqueados) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.beneficio = beneficio;
		this.estado = estado;
		this.retos = retos;
		this.artefactosDesbloqueados = artefactosDesbloqueados;
	}
    
	public Logro() {}

}
