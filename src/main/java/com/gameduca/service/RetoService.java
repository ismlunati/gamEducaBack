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

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoAsignatura;
import com.gameduca.entity.AlumnoReto;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.EstadoAlumnoAsignatura;
import com.gameduca.entity.EstadoAlumnoReto;
import com.gameduca.entity.Logro;
import com.gameduca.entity.Reto;
import com.gameduca.entity.RolNombre;
import com.gameduca.entity.dto.AlumnoRetoDTO;
import com.gameduca.entity.dto.RetoDTO;
import com.gameduca.entity.dto.mapper.AlumnoRetoDTOMapper;
import com.gameduca.entity.dto.mapper.LogroDTOMapper;
import com.gameduca.entity.dto.mapper.RetoDTOMapper;
import com.gameduca.repository.AlumnoRetoRepository;
import com.gameduca.repository.LogroRepository;
import com.gameduca.repository.RetoRepository;
import com.gameduca.utils.GameducaUtils;

@Service
@Transactional
public class RetoService {
	
    @Autowired
    RetoRepository retoRepository;
    
    @Autowired
    private AsignaturaService asignaturaService;
    
    @Autowired
    private AlumnoService alumnoService;
    
    @Autowired
    AlumnoRetoRepository alumnoRetoRepository;
    
    @Autowired
    private RetoDTOMapper retoDTOMapper;
    
    @Autowired
    private AlumnoRetoDTOMapper alumnoRetoDTOMapper;
    
    
    

    public List<Reto> obtenerRetosDeUnaAsignatura(Long asignaturaId) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	return asignatura.getRetos();
    }
    
    public List<RetoDTO> obtenerRetosDTODeUnaAsignatura(Long asignaturaId) throws Exception {
    	List<RetoDTO> listaRetos = new ArrayList<>();
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	for(Reto reto : asignatura.getRetos()) {
    		listaRetos.add(retoDTOMapper.toDTO(reto));
    	}
    	return listaRetos;
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
    
    public RetoDTO obtenerRetoDTO(Long idReto) {
    	Optional<Reto> reto = retoRepository.findById(idReto);
    	if(reto.isPresent()) {
    		return retoDTOMapper.toDTO(reto.get());
    	} else {
    		return retoDTOMapper.toDTO(new Reto());
    	}
    }
    
    public Reto añadirReto(Reto reto, Long asignaturaId) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(asignaturaId);
    	reto.setAsignatura(asignatura);
    	retoRepository.save(reto);
    	return reto;
    }
    
    public boolean asignarseReto(Long idReto) throws Exception {
    	boolean result = false;
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	String rol = usuario.getAuthorities().iterator().next().getAuthority();
    	String nombreUsuario = usuario.getUsername();
    	Reto reto = obtenerReto(idReto);
    	if(rol.equals(RolNombre.ROLE_USER.name())) {
    		GameducaUtils utils = new GameducaUtils();
    		if(reto.isTemporal() && utils.entraEnRangoHorario(reto.getFechaInicio(), reto.getFechaFin())) {
    			AlumnoReto alumnoReto = new AlumnoReto();
        		Alumno alumno = alumnoService.obtenerAlumnoPorNombre(nombreUsuario);
        		alumnoReto.setAlumno(alumno);
        		alumnoReto.setReto(reto);
        		alumnoReto.setEstado(EstadoAlumnoReto.EN_CURSO);
        		alumnoRetoRepository.save(alumnoReto);
        		result = true;
    		}
    	}	
    	return result;
    }
    
    public boolean finalizarReto(Reto reto) throws Exception {
    	boolean result = false;
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	String rol = usuario.getAuthorities().iterator().next().getAuthority();
    	String nombreUsuario = usuario.getUsername();
    	if(rol.equals(RolNombre.ROLE_USER.name())) {
    		GameducaUtils utils = new GameducaUtils();
    		if(reto.isTemporal() && utils.entraEnRangoHorario(reto.getFechaInicio(), reto.getFechaFin()) && !reto.isAutomatico()) {
    			AlumnoReto alumnoReto = alumnoRetoRepository.findAlumnoRetoByRetoyAlumno(nombreUsuario, reto.getId());
    			if(alumnoReto.getEstado().equals(EstadoAlumnoReto.EN_CURSO)) {
            		alumnoReto.setEstado(EstadoAlumnoReto.PETICION_ENVIADA);
            		alumnoRetoRepository.save(alumnoReto);
            		result = true;
    			}
    		}
    	}	
    	return result;
    }
    
    public boolean aceptarRechazarReto(Long idAlumnoReto, boolean aceptado) {
    	AlumnoReto alumnoReto = alumnoRetoRepository.findById(idAlumnoReto).get();
    	if(aceptado) {
    		alumnoReto.setEstado(EstadoAlumnoReto.COMPLETADO);
    	} else {
    		alumnoReto.setEstado(EstadoAlumnoReto.RECHAZADO);
    	}
    	alumnoRetoRepository.save(alumnoReto);
    	return true;
    }
    
    public Reto editarReto(Long idAsignatura, Long idReto, Reto newReto) throws Exception {
    	Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(idAsignatura);
    	return retoRepository.findById(idReto)
    	          .map(reto -> {
    	        	  reto.setNombre(newReto.getNombre());
    	        	  reto.setDescripcion(newReto.getDescripcion());
    	        	  reto.setPuntosOtorgados(newReto.getPuntosOtorgados());
    	        	  reto.setLogro(newReto.getLogro());
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

	public List<AlumnoRetoDTO> obtenerRetosAlumno(Long idAsignatura) {
		List<AlumnoRetoDTO> listaAlumnoRetoDTO = new ArrayList<>();
		for(AlumnoReto alumnoReto : alumnoRetoRepository.findAlumnoRetoByAsignatura(idAsignatura)) {
			listaAlumnoRetoDTO.add(alumnoRetoDTOMapper.toDTO(alumnoReto));
		}
		return listaAlumnoRetoDTO;
	}


}
