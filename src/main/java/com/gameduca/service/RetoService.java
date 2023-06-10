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
import org.springframework.web.bind.annotation.PostMapping;

import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Logro;
import com.gameduca.entity.Reto;
import com.gameduca.entity.RolNombre;
import com.gameduca.repository.AlumnoRetoRepository;
import com.gameduca.repository.LogroRepository;
import com.gameduca.repository.RetoRepository;

@Service
@Transactional
public class RetoService {
	
    @Autowired
    RetoRepository retoRepository;
    
    @Autowired
    private AsignaturaService asignaturaService;
    
    @Autowired
    AlumnoRetoRepository alumnoRetoRepository;
    

    public List<Reto> obtenerRetosDeUnaAsignatura(Long asignaturaId) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	return asignatura.getRetos();
    }
    
    public List<Reto> obtenerRetosDeUnAlumno(Long asignaturaId) throws Exception{
    	List<Reto> listaRetos = new ArrayList<>();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	String rol = usuario.getAuthorities().iterator().next().getAuthority();
    	String nombreUsuario = usuario.getUsername();
    	if(rol.equals(RolNombre.ROLE_USER.name())) {
    		listaRetos = alumnoRetoRepository.findRetosByAlumnoyAsignaturas(nombreUsuario, asignaturaId);
    	}
		return listaRetos;
    }
    
    public Reto obtenerReto(Long idReto) {
    	Optional<Reto> reto = retoRepository.findById(idReto);
    	if(reto.isPresent()) {
    		return reto.get();
    	} else {
    		return new Reto();
    	}
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
