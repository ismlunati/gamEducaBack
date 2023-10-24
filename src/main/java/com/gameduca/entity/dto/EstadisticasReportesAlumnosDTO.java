package com.gameduca.entity.dto;

public class EstadisticasReportesAlumnosDTO {
	
	private String nombreAlumno;
    private Long alumnoId;
    private Long cantidad;

    public EstadisticasReportesAlumnosDTO(String nombreAlumno, Long alumnoId, Long cantidad) {
        this.nombreAlumno = nombreAlumno;
        this.alumnoId = alumnoId;
        this.cantidad = cantidad;
    }

	public String getNombreAlumno() {
		return nombreAlumno;
	}

	public void setNombreAlumno(String nombreAlumno) {
		this.nombreAlumno = nombreAlumno;
	}

	public Long getAlumnoId() {
		return alumnoId;
	}

	public void setAlumnoId(Long alumnoId) {
		this.alumnoId = alumnoId;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

}
