package com.gameduca.entity.dto;

import java.util.List;

public class LogroDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;
//    private List<RetoDTO> retos;
    private List<ArtefactoLogroDTO> artefactoLogros;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public List<ArtefactoLogroDTO> getArtefactoLogros() {
		return artefactoLogros;
	}
	public void setArtefactoLogros(List<ArtefactoLogroDTO> artefactoLogros) {
		this.artefactoLogros = artefactoLogros;
	}

    
}

