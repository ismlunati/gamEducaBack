package com.gameduca.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "artefacto")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Artefacto extends BaseEntity{
	
    @NotNull
    @Column(name="NOMBRE")
    private String nombre;
    
    @NotNull
    @Column(name="DESCRIPCION")
    private String descripcion;
    
    @Column(name="COSTEPUNTOS")
    @NotNull(message = "Un reto tiene que otorgar puntos")
    @Min(value = 0, message = "Los puntos otorgados deben ser al menos 0")
    private Integer costePuntos;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name="ESTADO")
    private EstadoArtefacto estado;
    
    @NotNull
    @Column(name="TEMPORAL")
    private boolean temporal;
    
    @Column(name="FECHAINICIO")
    private Date fechaInicio;
    
    @Column(name="FECHAFIN")
    private Date fechaFin;
    
    @NotNull
    @Column(name="REPETIBLE")
    private boolean repetible;
    
    @JsonBackReference(value="asignatura-artefacto")
    @ManyToOne
    @JoinColumn(name = "ASIGNATURA_ID")
    private Asignatura asignatura;

    @JsonManagedReference(value="compra-artefacto")
    @OneToMany(mappedBy = "artefacto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras = new ArrayList<>();
    
    @JsonManagedReference(value="artefacto-artefactologro")
    @OneToOne(mappedBy = "artefacto", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArtefactoLogro artefactoLogros;
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCostePuntos() {
		return costePuntos;
	}

	public void setCostePuntos(Integer costePuntos) {
		this.costePuntos = costePuntos;
	}

	public EstadoArtefacto getEstado() {
		return estado;
	}

	public void setEstado(EstadoArtefacto estado) {
		this.estado = estado;
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
	

	public boolean isRepetible() {
		return repetible;
	}

	public void setRepetible(boolean repetible) {
		this.repetible = repetible;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

	public ArtefactoLogro getArtefactoLogros() {
		return artefactoLogros;
	}

	public void setArtefactoLogros(ArtefactoLogro artefactoLogros) {
		this.artefactoLogros = artefactoLogros;
	}

	public Artefacto(@NotNull String nombre, @NotNull String descripcion, @NotNull Integer costePuntos, @NotNull EstadoArtefacto estado,
			@NotNull boolean temporal, Date fechaInicio, Date fechaFin, Asignatura asignatura) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.costePuntos = costePuntos;
		this.estado = estado;
		this.temporal = temporal;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.asignatura = asignatura;
	}
    
    public Artefacto() {}
}
