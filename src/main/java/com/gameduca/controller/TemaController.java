package com.gameduca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Tema;
import com.gameduca.service.AsignaturaService;
import com.gameduca.service.TemaService;

@RestController
public class TemaController {
	
	@Autowired
	TemaService temaService;
	
    @GetMapping("/asignaturas/{idAsignatura}/temas")
    public List<Tema> obtenerTemasDeUnaAsignatura(@PathVariable Long idAsignatura) throws Exception{
    	return temaService.obtenerTemasDeUnaAsignatura(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/temas/{idTema}")
    public Tema obtenerTema(@PathVariable Long idTema) throws Exception{
    	return temaService.obtenerTema(idTema);
    }
	
    @PostMapping("/asignaturas/{idAsignatura}/temas")
    public void añadirTema(@RequestBody Tema tema, @PathVariable Long idAsignatura) throws Exception {
    	temaService.añadirTema(tema, idAsignatura);
    } 
    
    @PutMapping("/asignaturas/{idAsignatura}/temas/{idTema}")
    public Tema editarTema(@PathVariable Long idAsignatura, @PathVariable Long idTema, @RequestBody Tema newTema) throws Exception {
        return temaService.editarTema(idAsignatura, idTema, newTema);
    }
    
    @DeleteMapping("/asignaturas/{idAsignatura}/temas/{idTema}")
    public void deleteTema(@PathVariable Long idTema) {
    	temaService.borrarTema(idTema);
    }
}
