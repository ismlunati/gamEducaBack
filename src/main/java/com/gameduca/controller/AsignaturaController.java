package com.gameduca.controller;

import com.gameduca.entity.Asignatura;
import com.gameduca.repository.AsignaturaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    private AsignaturaRepository asignaturaRepository;
    
    @GetMapping("/asignaturas")
    public List<Asignatura> getAsignaturas() {
        return (List<Asignatura>) asignaturaRepository.findAll();
    }

    @PostMapping("/asignaturas")
    void addAsignatura(@RequestBody Asignatura asignatura) {
    	asignaturaRepository.save(asignatura);
    }
    

    @PutMapping("/asignaturas/{id}")
    public Asignatura updateAsignatura(@PathVariable Long id, @RequestBody Asignatura newAsignatura) {
        return asignaturaRepository.findById(id)
          .map(asignatura -> {
            asignatura.setNombre(newAsignatura.getNombre());
            asignatura.setDescripcion(newAsignatura.getDescripcion());
            asignatura.setCurso(newAsignatura.getCurso());
            return asignaturaRepository.save(asignatura);
          })
          .orElseGet(() -> {
            newAsignatura.setId(id);
            return asignaturaRepository.save(newAsignatura);
          });
    }

    @DeleteMapping("/asignaturas/{id}")
    public void deleteAsignatura(@PathVariable Long id) {
        asignaturaRepository.deleteById(id);
    }
    
    @GetMapping("/asignaturas/{id}")
    public Asignatura getAsignatura(@PathVariable Long id) throws Exception {
      Optional<Asignatura> asignatura = asignaturaRepository.findById(id);
      try {
    	  return asignatura.get();
      } catch (Exception e) {
    	  throw new Exception(e);
      }
    }

}
