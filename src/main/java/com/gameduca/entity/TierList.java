package com.gameduca.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class TierList extends BaseEntity {

	@Column(name="NOMBRE")
	@NotEmpty(message="El nombre no puede estar vacío") 
    private String nombre;
	
	@Column(name="DESCRIPCION")
	@NotEmpty(message="La descripcion no puede estar vacia") 
	String descripcion;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignatura asignatura;

	@OneToMany(mappedBy = "tierList", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tier> tiers;

    // Getters, setters y constructores
    
    public void addTier(Tier tier) {
        tiers.add(tier);
        tier.setTierList(this);
    }

    public void removeTier(Tier tier) {
        tiers.remove(tier);
        tier.setTierList(null);
    }
	
	
	public TierList() {}

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

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public List<Tier> getTiers() {
		return tiers;
	}

	public void setTiers(List<Tier> tiers) {
		this.tiers = tiers;
	}
}

