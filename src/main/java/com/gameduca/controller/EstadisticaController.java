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

import com.gameduca.entity.Artefacto;
import com.gameduca.entity.Tema;
import com.gameduca.entity.dto.ArtefactoCompraDTO;
import com.gameduca.entity.dto.ArtefactoDTO;
import com.gameduca.entity.dto.EstadisticasPreguntasPorAlumnosDTO;
import com.gameduca.entity.dto.EstadisticasPreguntasPorTemasDTO;
import com.gameduca.entity.dto.EstadisticasTestPorAlumnosDTO;
import com.gameduca.entity.dto.EstadisticasTestPorTestDTO;
import com.gameduca.service.ArtefactoService;
import com.gameduca.service.AsignaturaService;
import com.gameduca.service.CompraService;
import com.gameduca.service.EstadisticaService;

@RestController
public class EstadisticaController {

	@Autowired
	EstadisticaService estadisticaService;

	
    @GetMapping("/asignaturas/{idAsignatura}/estadisticasPreguntasPorTemas")
    public List<EstadisticasPreguntasPorTemasDTO> obtenerEstadisticasPreguntasPorTemas(@PathVariable Long idAsignatura) throws Exception{
    	return estadisticaService.obtenerEstadisticasPreguntasPorTemas(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/estadisticasPreguntasPorAlumnos")
    public List<EstadisticasPreguntasPorAlumnosDTO> obtenerEstadisticasPreguntasPorAlumnos(@PathVariable Long idAsignatura) throws Exception{
    	return estadisticaService.obtenerEstadisticasPreguntasPorAlumnos(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/estadisticasTestPorTest")
    public List<EstadisticasTestPorTestDTO> obtenerEstadisticasTestPorTest(@PathVariable Long idAsignatura) throws Exception{
    	return estadisticaService.obtenerEstadisticasTestPorTest(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/estadisticasTestPorAlumnos")
    public List<EstadisticasTestPorAlumnosDTO> obtenerEstadisticasTestPorAlumnos(@PathVariable Long idAsignatura) throws Exception{
    	return estadisticaService.obtenerEstadisticasTestPorAlumnos(idAsignatura);
    }
}