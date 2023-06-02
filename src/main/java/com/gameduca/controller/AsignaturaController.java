package com.gameduca.controller;

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

    @PutMapping("/asignaturas/{id}")
    public Asignatura updateAsignatura(@PathVariable Long id, @RequestBody Asignatura newAsignatura) {
        return asignaturaService.editarAsignatura(id, newAsignatura);
    }

    @DeleteMapping("/asignaturas/{id}")
    public void deleteAsignatura(@PathVariable Long id) {
    	asignaturaService.borrarAsignatura(id);
    }
    
    @GetMapping("/asignaturas/{id}")
    public Asignatura getAsignatura(@PathVariable Long id) throws Exception {
    	return asignaturaService.buscarAsignaturaPorId(id);
    }
    
    @PostMapping("/asignaturas/acceder")
    public boolean accederAsignatura(@RequestBody String codigo) {
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	boolean bool = asignaturaService.accederAsignatura(usuario.getUsername(), codigo);
        return bool;
    }

}
