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
import com.gameduca.entity.dto.ArtefactoDTO;
import com.gameduca.entity.dto.LogroDTO;
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
    
    @GetMapping("/asignaturas/{idAsignatura}/logrosDTO")
    public List<LogroDTO> obtenerLogrosDTODeUnaAsignatura(@PathVariable Long idAsignatura) throws Exception{
    	return logroService.obtenerLogrosDTODeUnaAsignatura(idAsignatura);
    }
	
    @GetMapping("/asignaturas/{idAsignatura}/logros/{idLogro}")
    public Logro obtenerLogro(@PathVariable Long idLogro) throws Exception{
    	return logroService.obtenerLogro(idLogro);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/logrosDTO/{idLogro}")
    public LogroDTO obtenerLogroDTO(@PathVariable Long idLogro) throws Exception{
    	LogroDTO logro = logroService.obtenerLogroDTO(idLogro);
    	return logro;
    }
    
    @PostMapping("/asignaturas/{idAsignatura}/logros")
    public Logro añadirLogro(@RequestBody Logro logro) throws Exception {
    	return logroService.añadirLogro(logro);
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
