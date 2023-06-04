package com.gameduca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Artefacto;
import com.gameduca.service.ArtefactoService;
import com.gameduca.service.PuntosService;

@RestController
public class PuntosController {
	
	@Autowired
	PuntosService puntosService;
	
    @PostMapping("/asignaturas/{idAsignatura}/{idAlumno}/asignarPuntos")
    public void asignarPuntos(@PathVariable Long idAsignatura, @PathVariable Long idAlumno, Integer puntos) throws Exception {
    	puntosService.asignarPuntos(idAlumno, idAsignatura, puntos);
    } 
    
    @PostMapping("/asignaturas/{idAsignatura}/{idAlumno}/detraerPuntos")
    public void detraerPuntos(@PathVariable Long idAsignatura, @PathVariable Long idAlumno, Integer puntos) throws Exception {
    	puntosService.detraerPuntos(idAlumno, idAsignatura, puntos);
    } 
	
	

}
