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
import com.gameduca.entity.dto.ArtefactoDTO;
import com.gameduca.service.ArtefactoService;
import com.gameduca.service.AsignaturaService;
import com.gameduca.service.CompraService;

@RestController
public class ArtefactoController {

	@Autowired
	ArtefactoService artefactoService;
	
    @Autowired
    private CompraService compraService;
	
    @GetMapping("/asignaturas/{idAsignatura}/artefactos")
    public List<Artefacto> obtenerArtefactosDeUnaAsignatura(@PathVariable Long idAsignatura) throws Exception{
    	return artefactoService.obtenerArtefactosDeUnaAsignatura(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/artefactosDTO")
    public List<ArtefactoDTO> obtenerArtefactosDTODeUnaAsignatura(@PathVariable Long idAsignatura) throws Exception{
    	return artefactoService.obtenerArtefactosDTODeUnaAsignatura(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/artefactosAlumno")
    public List<Artefacto> obtenerArtefactosDeUnAlumno(@PathVariable Long idAsignatura) throws Exception{
    	return compraService.obtenerTodosArtefactosUsuario(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/artefactos/{idArtefacto}")
    public Artefacto obtenerArtefacto(@PathVariable Long idArtefacto) throws Exception{
    	return artefactoService.obtenerArtefacto(idArtefacto);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/artefactosDTO/{idArtefacto}")
    public ArtefactoDTO obtenerArtefactoDTO(@PathVariable Long idArtefacto) throws Exception{
    	ArtefactoDTO artefacto = artefactoService.obtenerArtefactoDTO(idArtefacto);
    	return artefacto;
    }
	
    @PostMapping("/asignaturas/{idAsignatura}/artefactos")
    public Artefacto añadirArtefacto(@RequestBody Artefacto artefacto, @PathVariable Long idAsignatura) throws Exception {
    	return artefactoService.añadirArtefacto(artefacto, idAsignatura);
    } 
    
    @PutMapping("/asignaturas/{idAsignatura}/artefactos/{idArtefacto}")
    public Artefacto editarArtefacto(@PathVariable Long idAsignatura, @PathVariable Long idArtefacto, @RequestBody Artefacto newArtefacto) throws Exception {
        return artefactoService.editarArtefacto(idAsignatura, idArtefacto, newArtefacto);
    }
    
    @DeleteMapping("/asignaturas/{idAsignatura}/artefactos/{idArtefacto}")
    public void deleteArtefacto(@PathVariable Long idArtefacto) {
    	artefactoService.borrarArtefacto(idArtefacto);
    }
}
