package com.gameduca.controller;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Asignatura;
import com.gameduca.service.AsignaturaService;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AsignaturaController {
    
	@Autowired
	AsignaturaService asignaturaService;
    
    @GetMapping("/asignaturas")
    public List<Asignatura> getAsignaturasDeUsuario() {
        List<Asignatura> listaAsignaturas = asignaturaService.listaAsignaturasDelUsuario();
    	return listaAsignaturas;
    }

    @PostMapping("/asignaturas")
    void addAsignatura(@RequestBody Asignatura asignatura) {
    	asignaturaService.añadirAsignatura(asignatura);
    }    

    @PutMapping("/asignaturas/{idAsignatura}")
    public Asignatura updateAsignatura(@PathVariable Long idAsignatura, @RequestBody Asignatura newAsignatura) {
        return asignaturaService.editarAsignatura(idAsignatura, newAsignatura);
    }

    @DeleteMapping("/asignaturas/{idAsignatura}")
    public void deleteAsignatura(@PathVariable Long idAsignatura) {
    	asignaturaService.borrarAsignatura(idAsignatura);
    }
    
    @GetMapping("/asignaturas/{idAsignatura}")
    public Asignatura getAsignatura(@PathVariable Long idAsignatura) throws Exception {
    	return asignaturaService.buscarAsignaturaPorId(idAsignatura);
    }
    
    @PostMapping("/asignaturas/acceder")
    public boolean accederAsignatura(@RequestBody String codigo) {
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	boolean bool = asignaturaService.accederAsignatura(usuario.getUsername(), codigo);
        return bool;
    }
    
    @GetMapping("asignaturas/{idAsignatura}/listaSolicitudesPendientes")
    public List<Alumno> getAsignaturaListaSolicitudes(@PathVariable Long idAsignatura) throws Exception {
    	return asignaturaService.buscarSolicitudesPendientes(idAsignatura);
    }
    
    @PostMapping("asignaturas/{idAsignatura}/{idAlumno}/aceptar")
    public boolean aceptarAlumnoEnAsignatura(@PathVariable Long idAsignatura, @PathVariable Long idAlumno) throws Exception {
    	return asignaturaService.aceptarRechazarAlumno(idAsignatura, idAlumno, true);
    }
    
    @PostMapping("asignaturas/{idAsignatura}/{idAlumno}/rechazar")
    public boolean rechazarAlumnoEnAsignatura(@PathVariable Long idAsignatura, @PathVariable Long idAlumno) throws Exception {
    	return asignaturaService.aceptarRechazarAlumno(idAsignatura, idAlumno, false);
    }
}
