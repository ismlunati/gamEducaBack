package com.gameduca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Profesor;
import com.gameduca.repository.AlumnoRepository;
import com.gameduca.repository.ProfesorRepository;

@Service
@Transactional
public class ProfesorService {
	
    @Autowired
    ProfesorRepository profesorRepository;
    
    public void crearProfesor(Profesor profesor) {
    	profesorRepository.save(profesor);
    }

}
