package com.gameduca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Logro;
import com.gameduca.repository.LogroRepository;

//@Service
//@Transactional
//public class LogroService {
//	
//    @Autowired
//    LogroRepository logroRepository;
//    
//    @Autowired
//    private AsignaturaService asignaturaService;
//    
//
//    public List<Logro> obtenerLogrosDeUnAlumno(@PathVariable Long asignaturaId) throws Exception {
//    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
//    	return asignatura.getLogros();
//    }
//    
//    public void añadirLogro(Logro logro, Long asignaturaId) throws Exception {
//    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
//    	logro.setAsignatura(asignatura);
//    	logroRepository.save(logro);
//    }
//    
//    public Logro editarLogro(Long idAsignatura, Long idLogro, Logro newLogro) throws Exception {
//    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(idAsignatura);
//    	return logroRepository.findById(idLogro)
//    	          .map(logro -> {
//    	        	  logro.setNombre(newLogro.getNombre());
//    	        	  logro.setDescripcion(newLogro.getDescripcion());
//    	        	  logro.setPuntosOtorgados(newLogro.getPuntosOtorgados());
//    	        	  logro.setLogro(newLogro.getLogro());
//    	        	  logro.setTema(newLogro.getTema());
//    	        	  logro.setAsignatura(asignatura);
//    	            return logroRepository.save(logro);
//    	          })
//    	          .orElseGet(() -> {
//    	            newLogro.setId(idLogro);
//    	            return logroRepository.save(newLogro);
//    	          });
//    }
//    
//    public void borrarLogro(Long id) {
//    	logroRepository.deleteById(id);
//    }
//}
