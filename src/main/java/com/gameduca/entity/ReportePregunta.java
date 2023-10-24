package com.gameduca.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "reporte_pregunta")
public class ReportePregunta extends BaseEntity {

    @Column(name="TEXTO")
    @NotEmpty(message="El texto del reporte no puede estar vacio") 
    private String texto;
    
    @Enumerated(EnumType.STRING)
    @Column(name="MOTIVO")
    private MotivoReportePregunta motivo;
    
    @Enumerated(EnumType.STRING)
    @Column(name="ESTADO")
    private EstadoReportePregunta estado;
    
    @ManyToOne
    @JoinColumn(name = "pregunta_id", nullable = false)
    private Pregunta pregunta;
    
    @ManyToOne
    @JoinColumn(name = "alumno_id", nullable = false)
    private Alumno alumno;
    
    public ReportePregunta() {}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public MotivoReportePregunta getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoReportePregunta motivo) {
		this.motivo = motivo;
	}

	public EstadoReportePregunta getEstado() {
		return estado;
	}

	public void setEstado(EstadoReportePregunta estado) {
		this.estado = estado;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
 
}

