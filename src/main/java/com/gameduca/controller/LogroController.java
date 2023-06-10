package com.gameduca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gameduca.entity.Logro;
import com.gameduca.service.LogroService;


@RestController
public class LogroController {
	
	@Autowired
	LogroService logroService;
	
    @GetMapping("/asignaturas/{idAsignatura}/logrosAlumno")
    public List<Logro> obtenerLogrosDeUnAlumno(@PathVariable Long idAsignatura) throws Exception{
    	return logroService.obtenerLogrosDeUnAlumno(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/logros")
    public List<Logro> obtenerLogrosDeUnaAsignatura(@PathVariable Long idAsignatura) throws Exception{
    	return logroService.obtenerLogrosDeUnaAsignatura(idAsignatura);
    }
	
    @GetMapping("/asignaturas/{idAsignatura}/logros/{idLogro}")
    public Logro obtenerLogro(@PathVariable Long idLogro) throws Exception{
    	return logroService.obtenerLogro(idLogro);
    }
    
    @PostMapping("/asignaturas/{idAsignatura}/logros")
    public void añadirLogro(@RequestBody Logro logro) throws Exception {
    	logroService.añadirLogro(logro);
    } 
    
    @PutMapping("/asignaturas/{idAsignatura}/logros/{idLogro}")
    public Logro editarLogro(@PathVariable Long idLogro, @RequestBody Logro newLogro) throws Exception {
        return logroService.editarLogro(idLogro, newLogro);
    }
    
    @DeleteMapping("/asignaturas/{idAsignatura}/Logros/{idLogro}")
    public void deleteLogro(@PathVariable Long idLogro) {
    	logroService.borrarLogro(idLogro);
    }

}
