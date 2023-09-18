package com.gameduca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gameduca.entity.Pregunta;
import com.gameduca.entity.Test;
import com.gameduca.entity.dto.PreguntaElegibleDTO;
import com.gameduca.entity.dto.ResultadoTestDTO;
import com.gameduca.service.TestService;


@RestController
public class TestController {
	
	@Autowired
	TestService testService;
	
	
    @PostMapping("/asignaturas/{idAsignatura}/crearTest")
    public Test crearTest(@RequestBody Test test, @PathVariable Long idAsignatura, @RequestParam("selectedPreguntaIds") String selectedPreguntaIds) throws Exception {
    	System.out.println(selectedPreguntaIds);
    	return testService.crearTest(test, idAsignatura, selectedPreguntaIds);
    } 
    
    @GetMapping("/asignaturas/{idAsignatura}/test/{idTest}/realizarTest")
    public Pregunta realizarTest(@PathVariable Long idAsignatura, @PathVariable Long idTest,  @RequestParam("inicio") Boolean inicio, @RequestParam("idRespuesta") Long idRespuesta) throws Exception {
    	return testService.realizarTest(idAsignatura, idTest, inicio, idRespuesta);
    }
    
    
    @GetMapping("/asignaturas/{idAsignatura}/test")
    public List<Test> obtenerTest(@PathVariable Long idAsignatura) throws Exception {
    	return testService.obtenerTest(idAsignatura);
    } 

    
    @GetMapping("/asignaturas/{idAsignatura}/test/preguntasElegibles")
    public List<PreguntaElegibleDTO> preguntasElegibles(@PathVariable Long idAsignatura, @RequestParam String listaTemas) throws Exception {
    	return testService.getPreguntasElegibles(idAsignatura, listaTemas);
    } 
    
    @GetMapping("/asignaturas/{idAsignatura}/test/{idTest}/resultadoTest")
    public ResultadoTestDTO resultadoTest(@PathVariable Long idAsignatura, @PathVariable Long idTest) throws Exception {
    	return testService.resultado(idAsignatura, idTest);
    } 


}
