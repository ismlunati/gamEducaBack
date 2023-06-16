package com.gameduca.entity.dto;

public class ArtefactoLogroDTO {

    private Long id;
    private boolean desbloquear;
    private boolean obtener;
    private ArtefactoDTO artefacto; 
    private LogroDTO logro;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public ArtefactoDTO getArtefacto() {
		return artefacto;
	}
	public void setArtefacto(ArtefactoDTO artefacto) {
		this.artefacto = artefacto;
	}
	public LogroDTO getLogro() {
		return logro;
	}
	public void setLogro(LogroDTO logro) {
		this.logro = logro;
	} 

    // Getters and setters...
    
    
}
