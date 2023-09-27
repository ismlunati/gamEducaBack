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
import com.gameduca.entity.ReportePregunta;
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
    
    @PostMapping("/asignaturas/{idAsignatura}/test/{idTest}/reportarPregunta/{idPregunta}")
    public ReportePregunta crearReportarPregunta(@RequestBody ReportePregunta reportePregunta, @PathVariable Long idPregunta) {
    	return preguntaService.crearReportarPregunta(reportePregunta, idPregunta);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/reportePreguntas")
    public List<ReportePregunta> obtenerReportarPregunta(@PathVariable Long idAsignatura) {
    	return preguntaService.crearReportarPregunta(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/reportePreguntas/{idReportePregunta}/aceptar")
    public ReportePregunta aceptarReportarPregunta(@PathVariable Long idReportePregunta) {
    	return preguntaService.aceptarRechazarReportarPregunta(idReportePregunta, true);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/reportePreguntas/{idReportePregunta}/rechazar")
    public ReportePregunta rechazarReportarPregunta(@PathVariable Long idReportePregunta) {
    	return preguntaService.aceptarRechazarReportarPregunta(idReportePregunta, false);
    }
}
