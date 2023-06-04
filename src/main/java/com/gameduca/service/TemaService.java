package com.gameduca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Tema;
import com.gameduca.repository.AsignaturaRepository;
import com.gameduca.repository.TemaRepository;

@Service
@Transactional
public class TemaService {
	
    @Autowired
    private TemaRepository temaRepository;
    
    @Autowired
    private AsignaturaService asignaturaService;
    

    public List<Tema> obtenerTemasDeUnaAsignatura(@PathVariable Long asignaturaId) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	return asignatura.getTemas();
    }
    
    public void añadirTema(Tema tema, Long asignaturaId) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	tema.setAsignatura(asignatura);
    	temaRepository.save(tema);
    }
    
    public Tema editarTema(Long idAsignatura, Long idTema, Tema newTema) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(idAsignatura);
    	return temaRepository.findById(idTema)
    	          .map(tema -> {
    	        	  tema.setNombre(newTema.getNombre());
    	        	  tema.setDescripcion(newTema.getDescripcion());
    	        	  tema.setAsignatura(asignatura);
    	            return temaRepository.save(tema);
    	          })
    	          .orElseGet(() -> {
    	            newTema.setId(idTema);
    	            return temaRepository.save(newTema);
    	          });
    }
    
    public void borrarTema(Long id) {
    	temaRepository.deleteById(id);
    }

}
