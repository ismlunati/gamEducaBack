package com.gameduca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Reto;
import com.gameduca.repository.LogroRepository;
import com.gameduca.repository.RetoRepository;

@Service
@Transactional
public class RetoService {
	
    @Autowired
    RetoRepository retoRepository;
    
    @Autowired
    private AsignaturaService asignaturaService;
    

    public List<Reto> obtenerRetosDeUnaAsignatura(@PathVariable Long asignaturaId) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	return asignatura.getRetos();
    }
    
    public void añadirReto(Reto reto, Long asignaturaId) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	reto.setAsignatura(asignatura);
    	retoRepository.save(reto);
    }
    
    public Reto editarReto(Long idAsignatura, Long idReto, Reto newReto) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(idAsignatura);
    	return retoRepository.findById(idReto)
    	          .map(reto -> {
    	        	  reto.setNombre(newReto.getNombre());
    	        	  reto.setDescripcion(newReto.getDescripcion());
    	        	  reto.setPuntosOtorgados(newReto.getPuntosOtorgados());
    	        	  reto.setLogro(newReto.getLogro());
    	        	  reto.setTema(newReto.getTema());
    	        	  reto.setTemporal(newReto.isTemporal());
    	        	  if(newReto.isTemporal()) {
    	        		  reto.setFechaInicio(newReto.getFechaInicio());
    	        		  reto.setFechaFin(newReto.getFechaFin());
    	        	  }
    	        	  reto.setAsignatura(asignatura);
    	            return retoRepository.save(reto);
    	          })
    	          .orElseGet(() -> {
    	            newReto.setId(idReto);
    	            return retoRepository.save(newReto);
    	          });
    }
    
    public void borrarReto(Long id) {
    	retoRepository.deleteById(id);
    }


}
