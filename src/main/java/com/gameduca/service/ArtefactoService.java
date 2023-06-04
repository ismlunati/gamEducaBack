package com.gameduca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gameduca.entity.Artefacto;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Tema;
import com.gameduca.repository.ArtefactoRepository;
import com.gameduca.repository.AsignaturaRepository;

@Service
@Transactional
public class ArtefactoService {
	
    @Autowired
    private ArtefactoRepository artefactoRepository;
    
    @Autowired
    private AsignaturaService asignaturaService;
    
    public List<Artefacto> obtenerArtefactosDeUnaAsignatura(@PathVariable Long asignaturaId) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	return asignatura.getArtefactos();
    }
    
    @PostMapping("/asignaturas/{idAsignatura}")
    public void añadirArtefacto(Artefacto artefacto, Long asignaturaId) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	artefacto.setAsignatura(asignatura);
    	artefactoRepository.save(artefacto);
    }
    
    public Artefacto editarArtefacto(Long idAsignatura, Long idArtefacto, Artefacto newArtefacto) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(idAsignatura);
    	return artefactoRepository.findById(idArtefacto)
    	          .map(artefacto -> {
    	        	  artefacto.setNombre(newArtefacto.getNombre());
    	        	  artefacto.setDescripcion(newArtefacto.getDescripcion());
    	        	  artefacto.setCostePuntos(newArtefacto.getCostePuntos());
    	        	  artefacto.setEstado(newArtefacto.getEstado());
    	        	  artefacto.setTemporal(newArtefacto.isTemporal());
    	        	  artefacto.setFechaInicio(newArtefacto.getFechaInicio());
    	        	  artefacto.setFechaFin(newArtefacto.getFechaFin());
    	        	  artefacto.setAsignatura(asignatura);
    	            return artefactoRepository.save(artefacto);
    	          })
    	          .orElseGet(() -> {
    	        	  newArtefacto.setId(idArtefacto);
    	            return artefactoRepository.save(newArtefacto);
    	          });
    }
    
    public void borrarArtefacto(Long id) {
    	artefactoRepository.deleteById(id);
    }

}
