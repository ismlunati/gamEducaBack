package com.gameduca.entity.dto;

import java.util.List;
import java.util.Map;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.TierList;

public class ListaAlumnosAndTierListDTO {
	
	private List<Alumno> listaAlumnos;
	
	private TierList tierList;

	public ListaAlumnosAndTierListDTO() {}

	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

	public TierList getTierList() {
		return tierList;
	}

	public void setTierList(TierList tierList) {
		this.tierList = tierList;
	}


}
