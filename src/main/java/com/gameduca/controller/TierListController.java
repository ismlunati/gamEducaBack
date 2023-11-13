package com.gameduca.controller;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.TierList;
import com.gameduca.entity.dto.AlumnosAndTiersDTO;
import com.gameduca.entity.dto.ListaAlumnosAndTierListDTO;
import com.gameduca.service.AsignaturaService;
import com.gameduca.service.TierListService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TierListController {
    
	@Autowired
	TierListService tierListService;
    
    @GetMapping("/asignaturas/{idAsignatura}/estadisticas/tierList")
    public List<TierList> getTierList(@PathVariable Long idAsignatura) {
    	return tierListService.getTierList(idAsignatura);
    }

    @PostMapping("/asignaturas/{idAsignatura}/estadisticas/tierList")
    public TierList addTierList(@PathVariable Long idAsignatura, @RequestBody TierList tierList) throws Exception {
    	return tierListService.crearTierList(idAsignatura, tierList);   	
    }    

    @PostMapping("/asignaturas/{idAsignatura}/estadisticas/tierList/{idTierList}/añadirAlumnosATiers")
    public TierList addAlumnosATiers(@PathVariable Long idAsignatura, @PathVariable Long idTierList, @RequestBody AlumnosAndTiersDTO alumnosAndTiers) throws Exception {
    	return tierListService.addAlumnosATiers(idAsignatura, idTierList, alumnosAndTiers);   	
    }  
    
    @GetMapping("/asignaturas/{idAsignatura}/estadisticas/tierList/{idTierList}")
    public ListaAlumnosAndTierListDTO getTierListAndListaAlumnos(@PathVariable Long idAsignatura, @PathVariable Long idTierList) throws Exception {
    	return tierListService.getTierListAndListaAlumnos(idAsignatura, idTierList);
    }
}