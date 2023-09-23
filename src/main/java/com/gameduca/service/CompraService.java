package com.gameduca.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoAsignatura;
import com.gameduca.entity.Artefacto;
import com.gameduca.entity.Compra;
import com.gameduca.entity.EstadoArtefacto;
import com.gameduca.entity.EstadoCompra;
import com.gameduca.entity.Reto;
import com.gameduca.entity.RolNombre;
import com.gameduca.entity.dto.ArtefactoCompraDTO;
import com.gameduca.entity.dto.mapper.ArtefactoCompraDTOMapper;
import com.gameduca.repository.CompraRepository;
import com.gameduca.repository.LogroRepository;

@Service
@Transactional
public class CompraService {
	
    @Autowired
    CompraRepository compraRepository;
    
    @Autowired
    ArtefactoService artefactoService;
    
    @Autowired
    AlumnoService alumnoService;
    
    @Autowired
    PuntosService puntosService;
    
    @Autowired
    ArtefactoCompraDTOMapper artefactoCompraDTOMapper;
    
    public List<Compra> obtenerTodasComprasUsuario(Long idAsignatura){
    	List<Compra> listaCompras = new ArrayList<>();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	String rol = usuario.getAuthorities().iterator().next().getAuthority();
    	String nombreUsuario = usuario.getUsername();
    	if(rol.equals(RolNombre.ROLE_USER.name())) {
    		listaCompras = compraRepository.findComprasByAlumnoyAsignaturas(nombreUsuario, idAsignatura);
    	}
    	return listaCompras;
    }
    
    public List<ArtefactoCompraDTO> obtenerTodosArtefactosUsuario(Long idAsignatura){
    	List<ArtefactoCompraDTO> listaArtefactoCompraDTO = new ArrayList<>();
    	List<Compra> listaCompra = new ArrayList<>();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	String rol = usuario.getAuthorities().iterator().next().getAuthority();
    	String nombreUsuario = usuario.getUsername();
    	if(rol.equals(RolNombre.ROLE_USER.name())) {
    		listaCompra = compraRepository.findArtefactosByAlumnoyAsignaturas(nombreUsuario, idAsignatura);
    	} else {
    		listaCompra = compraRepository.findComprasByAsignaturas(idAsignatura);
    	}
		listaArtefactoCompraDTO = artefactoCompraDTOMapper.toDTO(listaCompra);
    	return listaArtefactoCompraDTO;
    }
    
    public Compra obtenerCompra(Long idCompra) {
    	Optional<Compra> compra = compraRepository.findById(idCompra);
    	if(compra.isPresent()) {
    		return compra.get();
    	} else {
    		return new Compra();
    	}
    }
    
    public Compra crearCompra(Long idArtefacto, Long idAsignatura) throws Exception {
    	Compra compra = new Compra();
    	Artefacto artefacto = artefactoService.obtenerArtefacto(idArtefacto);
    	if(!artefacto.isNew() && artefacto.getEstado().equals(EstadoArtefacto.PUBLICADO)) {
    		UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
    				.getPrincipal();
        	String rol = usuario.getAuthorities().iterator().next().getAuthority();
        	String nombreUsuario = usuario.getUsername();
        	Alumno alumno = alumnoService.obtenerAlumnoPorNombre(nombreUsuario);
        	if(rol.equals(RolNombre.ROLE_USER.name())) {
        		if(artefacto.isTemporal()) {
        			Date hoy = new Date();
        			if((hoy.equals(artefacto.getFechaInicio()) || hoy.after(artefacto.getFechaInicio())) 
        					&& (hoy.equals(artefacto.getFechaFin()) || hoy.before(artefacto.getFechaFin()))) {
        				compra = crearCompraAuxiliar(alumno, artefacto, idAsignatura, compra);
        			}
        		} else {
    				compra = crearCompraAuxiliar(alumno, artefacto, idAsignatura, compra);
        		}        	
        	}
    		
    	}
    	compraRepository.save(compra);
    	return compra;
    }
    
    public Compra crearCompraAuxiliar(Alumno alumno, Artefacto artefacto, Long idAsignatura, Compra compra) {  	
    	AlumnoAsignatura alumnoAsignatura = alumnoService.obtenerAlumnoAsignaturaPorNombreAlumnoAsignaturaId(alumno.getUsuario().getNombreUsuario(), idAsignatura);
		if(artefacto.getCostePuntos()<= alumnoAsignatura.getPuntos()) {
			compra.setAlumno(alumno);
			compra.setArtefacto(artefacto);
			compra.setEstado(EstadoCompra.COMPRADO); 
		}
    	return compra;
    }
    
    public boolean canjearCompra(Long idCompra) {
    	boolean result = false;
    	Compra compra = compraRepository.findById(idCompra).get();
    	if(compra.getEstado().equals(EstadoCompra.COMPRADO)) {
    		compra.setEstado(EstadoCompra.PETICION_DE_USO);
    		compraRepository.save(compra);
    		result = true;
    	}
    	return result;
    }
    
    public boolean aceptarRechazarCanjeoCompra(Long idCompra, Long idAsignatura, boolean aceptar) {
    	boolean result = false;
    	Compra compra = compraRepository.findById(idCompra).get();
    	if(compra.getEstado().equals(EstadoCompra.PETICION_DE_USO)) {
    		if(aceptar) {
        		compra.setEstado(EstadoCompra.CANJEADO);
    		} else {
        		compra.setEstado(EstadoCompra.RECHAZADO);
        		puntosService.asignarPuntos(compra.getAlumno().getId(), idAsignatura, compra.getArtefacto().getCostePuntos());
    		}
    		compraRepository.save(compra);
    		result = true;
    	}
    	return result;
    }

}
