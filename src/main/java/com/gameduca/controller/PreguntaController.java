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
import com.gameduca.service.PreguntaService;
import com.gameduca.service.TestService;


@RestController
public class PreguntaController {
	
	@Autowired
	PreguntaService preguntaService;
	
	
    @PostMapping("/asignaturas/{idAsignatura}/temas/{idTema}/crearPregunta")
    public Pregunta crearPregunta(@RequestBody Pregunta pregunta, @PathVariable Long idTema, @RequestParam("respuestas") String respuestas, @RequestParam("respuestaCorrecta") String respuestaCorrecta) throws Exception {
    	return preguntaService.crearPregunta(pregunta, idTema, respuestas,respuestaCorrecta);
    } 

}
