package com.gameduca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoAsignatura;
import com.gameduca.repository.AlumnoAsignaturaRepository;
import com.gameduca.repository.AlumnoRepository;
import com.gameduca.repository.UsuarioRepository;

@Service
@Transactional
public class AlumnoService {
	
    @Autowired
    AlumnoRepository alumnoRepository;
    
    @Autowired
    AlumnoAsignaturaRepository alumnoAsignaturaRepository;
    
    public void crearAlumno(Alumno alumno) {
    	alumnoRepository.save(alumno);
    }
    
    public Alumno obtenerAlumnoPorNombre(String nombreAlumno) {
    	return alumnoRepository.findAlumnoByNombreUsuario(nombreAlumno);
    }
    
    public AlumnoAsignatura obtenerAlumnoAsignaturaPorNombreAlumnoAsignaturaId(String nombreAlumno, Long AsignaturaId) {
    	return alumnoAsignaturaRepository.findAlumnoAsignaturaByNombreAlumnoIdAsignatura(AsignaturaId, nombreAlumno);
    }
    
    public Alumno obtenerAlumnoPorId(Long idAlumno) {
    	return alumnoRepository.findById(idAlumno).get();
    }

}
