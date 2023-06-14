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

import com.gameduca.entity.Reto;
import com.gameduca.entity.Tema;
import com.gameduca.service.RetoService;
import com.gameduca.service.TemaService;

@RestController
public class RetoController {
	
	@Autowired
	RetoService retoService;
	
    @GetMapping("/asignaturas/{idAsignatura}/retos")
    public List<Reto> obtenerRetosDeUnaAsignatura(@PathVariable Long idAsignatura) throws Exception{
    	return retoService.obtenerRetosDeUnaAsignatura(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/retosAlumno")
    public List<Reto> obtenerRetosDeUnAlumno(@PathVariable Long idAsignatura) throws Exception{
    	return retoService.obtenerRetosDeUnAlumno(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}/retos/{idReto}")
    public Reto obtenerReto(@PathVariable Long idReto) throws Exception{
    	return retoService.obtenerReto(idReto);
    }
	
    @PostMapping("/asignaturas/{idAsignatura}/retos")
    public void añadirReto(@RequestBody Reto reto, @PathVariable Long idAsignatura) throws Exception {
    	retoService.añadirReto(reto, idAsignatura);
    }
    
    @PostMapping("/asignaturas/{idAsignatura}/asignarseReto")
    public boolean asignarseReto(@RequestBody Reto reto) throws Exception {
    	return retoService.asignarseReto(reto);
    } 
    
    @PostMapping("/asignaturas/{idAsignatura}/finalizarReto")
    public boolean finalizarReto(@RequestBody Reto reto) throws Exception {
    	return retoService.finalizarReto(reto);
    } 
    
    @PostMapping("asignaturas/{idAsignatura}/retoAceptar/{idAlumnoReto}")
    public boolean aceptarReto(@PathVariable Long idAlumnoReto) throws Exception {
    	return retoService.aceptarRechazarReto(idAlumnoReto, true);
    }
    
    @PostMapping("asignaturas/{idAsignatura}/retoRechazar/{idAlumnoReto}")
    public boolean rechazarReto(@PathVariable Long idAlumnoReto) throws Exception {
    	return retoService.aceptarRechazarReto(idAlumnoReto, false);
    }
    
    @PutMapping("/asignaturas/{idAsignatura}/retos/{idReto}")
    public Reto editarReto(@PathVariable Long idAsignatura, @PathVariable Long idReto, @RequestBody Reto newReto) throws Exception {
        return retoService.editarReto(idAsignatura, idReto, newReto);
    }
    
    @DeleteMapping("/asignaturas/{idAsignatura}/retos/{idReto}")
    public void deleteReto(@PathVariable Long idReto) {
    	retoService.borrarReto(idReto);
    }

}
