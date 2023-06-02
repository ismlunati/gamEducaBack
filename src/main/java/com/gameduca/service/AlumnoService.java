package com.gameduca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.entity.Alumno;
import com.gameduca.repository.AlumnoRepository;
import com.gameduca.repository.UsuarioRepository;

@Service
@Transactional
public class AlumnoService {
	
    @Autowired
    AlumnoRepository alumnoRepository;
    
    public void crearAlumno(Alumno alumno) {
    	alumnoRepository.save(alumno);
    }

}
