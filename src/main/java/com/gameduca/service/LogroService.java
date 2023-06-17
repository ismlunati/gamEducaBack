package com.gameduca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Logro;
import com.gameduca.entity.Reto;
import com.gameduca.entity.RolNombre;
import com.gameduca.entity.dto.LogroDTO;
import com.gameduca.entity.dto.mapper.ArtefactoDTOMapper;
import com.gameduca.entity.dto.mapper.LogroDTOMapper;
import com.gameduca.repository.AlumnoRetoRepository;
import com.gameduca.repository.LogroRepository;

@Service
@Transactional
public class LogroService {
	
    @Autowired
    LogroRepository logroRepository;
    
    @Autowired
    RetoService retoService;
    
    @Autowired
    private LogroDTOMapper logroDTOMapper;
    
    public List<Logro> obtenerLogrosDeUnaAsignatura(Long idAsignatura) throws Exception {
    	List<Logro> listaLogros = new ArrayList<>();
		List<Reto> listaRetosDeAsignatura = retoService.obtenerRetosDeUnaAsignatura(idAsignatura);
		for(Reto reto: listaRetosDeAsignatura) {
			Logro logro = reto.getLogro();
			if(!listaLogros.contains(logro)) {
				listaLogros.add(logro);
			}
		}
		return listaLogros;
    }
    
    public List<LogroDTO> obtenerLogrosDTODeUnaAsignatura(Long idAsignatura) throws Exception {
    	List<LogroDTO> listaLogros = new ArrayList<>();
		List<Reto> listaRetosDeAsignatura = retoService.obtenerRetosDeUnaAsignatura(idAsignatura);
		for(Reto reto: listaRetosDeAsignatura) {
			Logro logro = reto.getLogro();
			if(!listaLogros.contains(logro)) {
				LogroDTO logroDTO = logroDTOMapper.toDTO(logro);
				listaLogros.add(logroDTO);
			}
		}
		return listaLogros;
    }

    public List<Logro> obtenerLogrosDeUnAlumno(Long idAsignatura) throws Exception {
    	List<Logro> listaLogros = new ArrayList<>();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	String rol = usuario.getAuthorities().iterator().next().getAuthority();
    	if(rol.equals(RolNombre.ROLE_USER.name())) {
    		List<Reto> listaRetos = retoService.obtenerRetosDeUnAlumno(idAsignatura);
    		for(Reto reto: listaRetos) {
    			Logro logro = reto.getLogro();
    			if(!listaLogros.contains(logro)) {
    				listaLogros.add(logro);
    			}
    		}
    	}
		return listaLogros;
    }
    
    public Logro obtenerLogro(Long logroId) throws Exception {
    	Optional<Logro> logro = logroRepository.findById(logroId);
    	if(logro.isPresent()) {
    		return logro.get();
    	} else {
    		return new Logro();
    	}
    }
    
    public LogroDTO obtenerLogroDTO(Long logroId) throws Exception {
    	Optional<Logro> logro = logroRepository.findById(logroId);
    	if(logro.isPresent()) {
    		return logroDTOMapper.toDTO(logro.get()); 
    	} else {
    		return logroDTOMapper.toDTO(new Logro());
    	}
    }
    
    public Logro añadirLogro(Logro logro) throws Exception {
    	logroRepository.save(logro);
    	return logro;
    }
    
    public Logro editarLogro(Long idLogro, Logro newLogro) throws Exception {
    	return logroRepository.findById(idLogro)
    	          .map(logro -> {
    	        	  logro.setNombre(newLogro.getNombre());
    	        	  logro.setDescripcion(newLogro.getDescripcion());
    	        	  logro.setRetos(newLogro.getRetos());
    	        	  logro.setArtefactoLogros(newLogro.getArtefactoLogros());
    	            return logroRepository.save(logro);
    	          })
    	          .orElseGet(() -> {
    	            newLogro.setId(idLogro);
    	            return logroRepository.save(newLogro);
    	          });
    }
    
    public void borrarLogro(Long id) {
    	logroRepository.deleteById(id);
    }
}
