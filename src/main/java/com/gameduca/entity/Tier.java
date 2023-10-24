package com.gameduca.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tier extends BaseEntity {

	@Column(name="NOMBRE")
	@NotEmpty(message="El nombre no puede estar vacío") 
    private String nombre;
	
	@Column(name="COLOR")
	@NotEmpty(message="El color no puede estar vacío") 
    private String color;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tier_list_id")
    private TierList tierList;

    @ManyToMany
    @JoinTable(
      name = "tier_alumno",
      joinColumns = @JoinColumn(name = "tier_id"),
      inverseJoinColumns = @JoinColumn(name = "alumno_id"))
    private List<Alumno> alumnos;


    // Getters, setters y constructores
    
	public Tier() {	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public TierList getTierList() {
		return tierList;
	}

	public void setTierList(TierList tierList) {
		this.tierList = tierList;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

}
