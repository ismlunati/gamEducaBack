package com.gameduca.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "artefacto_logro")
public class ArtefactoLogro extends BaseEntity{

    private boolean desbloquear;
    
    private boolean obtener;
    
    @JsonBackReference(value="artefacto-artefactologro")
    @OneToOne
    @JoinColumn(name = "artefacto_id")
    private Artefacto artefacto;

    @JsonBackReference(value="logro-artefactologro")
    @ManyToOne
    @JoinColumn(name = "logro_id")
    private Logro logro;

	public boolean isDesbloquear() {
		return desbloquear;
	}

	public void setDesbloquear(boolean desbloquear) {
		this.desbloquear = desbloquear;
	}

	public boolean isObtener() {
		return obtener;
	}

	public void setObtener(boolean obtener) {
		this.obtener = obtener;
	}

	public Artefacto getArtefacto() {
		return artefacto;
	}

	public void setArtefacto(Artefacto artefacto) {
		this.artefacto = artefacto;
	}

	public Logro getLogro() {
		return logro;
	}

	public void setLogro(Logro logro) {
		this.logro = logro;
	}

	public ArtefactoLogro(boolean desbloquear, boolean obtener, Artefacto artefacto, Logro logro) {
		super();
		this.desbloquear = desbloquear;
		this.obtener = obtener;
		this.artefacto = artefacto;
		this.logro = logro;
	}

	public ArtefactoLogro() {}
}