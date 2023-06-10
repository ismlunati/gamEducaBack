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
import com.gameduca.entity.Reto;
import com.gameduca.entity.RolNombre;
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
    
    public List<Artefacto> obtenerTodosArtefactosUsuario(Long idAsignatura){
    	List<Artefacto> listaArtefacto = new ArrayList<>();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	String rol = usuario.getAuthorities().iterator().next().getAuthority();
    	String nombreUsuario = usuario.getUsername();
    	if(rol.equals(RolNombre.ROLE_USER.name())) {
    		listaArtefacto = compraRepository.findArtefactosByAlumnoyAsignaturas(nombreUsuario, idAsignatura);
    	}
    	return listaArtefacto;
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
    	if(!artefacto.isNew() && artefacto.getEstado().equals("Activo")) {
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
    	return compra;
    }
    
    public Compra crearCompraAuxiliar(Alumno alumno, Artefacto artefacto, Long idAsignatura, Compra compra) {  	
    	AlumnoAsignatura alumnoAsignatura = alumnoService.obtenerAlumnoAsignaturaPorNombreAlumnoAsignaturaId(alumno.getUsuario().getNombreUsuario(), idAsignatura);
		if(artefacto.getCostePuntos()<= alumnoAsignatura.getPuntos()) {
			compra.setAlumno(alumno);
			compra.setArtefacto(artefacto);
			compra.setCantidad(1);
			compra.setEstado("Pendiente Aceptacion");
		}
    	return compra;
    }

}
