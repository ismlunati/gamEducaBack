package com.gameduca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoAsignatura;
import com.gameduca.repository.AlumnoAsignaturaRepository;
import com.gameduca.repository.ArtefactoRepository;

@Service
@Transactional
public class PuntosService {
	
    @Autowired
    private AlumnoAsignaturaRepository alumnoAsignaturaRepository;
    
	public void asignarPuntos(Long idAlumno, Long idAsignatura, Integer puntos) {
		AlumnoAsignatura alumnoAsignatura = alumnoAsignaturaRepository.findAlumnoAsignaturaByIdAlumnoIdAsignatura(idAsignatura, idAlumno);
		Integer puntosTotales = alumnoAsignatura.getPuntos() + puntos;
		alumnoAsignatura.setPuntos(puntosTotales);
		alumnoAsignaturaRepository.save(alumnoAsignatura);
	}
	
	
	public void detraerPuntos(Long idAlumno, Long idAsignatura, Integer puntos) {
		AlumnoAsignatura alumnoAsignatura = alumnoAsignaturaRepository.findAlumnoAsignaturaByIdAlumnoIdAsignatura(idAsignatura, idAlumno);
		Integer puntosTotales = alumnoAsignatura.getPuntos() + puntos;
		if(puntosTotales<0) {
			puntosTotales = 0;
		}
		alumnoAsignatura.setPuntos(puntosTotales);
		alumnoAsignaturaRepository.save(alumnoAsignatura);
	}

}
