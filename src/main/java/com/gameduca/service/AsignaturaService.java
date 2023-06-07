package com.gameduca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoAsignatura;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Profesor;
import com.gameduca.entity.Rol;
import com.gameduca.entity.RolNombre;
import com.gameduca.repository.AlumnoAsignaturaRepository;
import com.gameduca.repository.AlumnoRepository;
import com.gameduca.repository.AsignaturaRepository;
import com.gameduca.repository.ProfesorRepository;

@Service
@Transactional
public class AsignaturaService {
	
    @Autowired
    private AsignaturaRepository asignaturaRepository;
    
    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @Autowired
    private ProfesorRepository profesorRepository;
    
    @Autowired
    private AlumnoAsignaturaRepository alumnoAsignaturaRepository;
    
    public List<Asignatura> listaAsignaturasDelUsuario(){
    	List<Asignatura> listaAsignaturas = new ArrayList<>();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	String rol = usuario.getAuthorities().iterator().next().getAuthority();
    	String nombreUsuario = usuario.getUsername();
    	if(rol.equals(RolNombre.ROLE_ADMIN.name())) {
    		listaAsignaturas = asignaturaRepository.findAsignaturasByProfesor(nombreUsuario);
    	} else {
    		listaAsignaturas = alumnoAsignaturaRepository.findAsignaturasByAlumno(nombreUsuario);
    	}
		return listaAsignaturas;
    }
    
    public Asignatura añadirAsignatura(Asignatura asignatura) {
    	String codigo = null;
    	List<String> listaCodigos = asignaturaRepository.findAllCodigoAsignatura();
    	while(null == codigo || listaCodigos.contains(codigo)) {
    		codigo = generateRandomCode();
    	}
    	asignatura.setCodigo(codigo);
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	Profesor profesor = profesorRepository.findProfesorByNombre(usuario.getUsername());
    	asignatura.setProfesor(profesor);
    	//usuario.getUsername()
    	asignaturaRepository.save(asignatura);
    	return asignatura;
    }
    
    public Asignatura editarAsignatura(Long id, Asignatura newAsignatura) {
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
    
    public void borrarAsignatura(Long id) {
    	asignaturaRepository.deleteById(id);
    }
	
    public Asignatura buscarAsignaturaPorId(Long id) throws Exception {
        Optional<Asignatura> asignatura = asignaturaRepository.findById(id);
        try {
      	  return asignatura.get();
        } catch (Exception e) {
      	  throw new Exception(e);
        }
    }
    
    public boolean accederAsignatura(String nombreUsuario, String codigo) {
    	Asignatura asignatura = asignaturaRepository.findAsignaturaByCodigo(codigo);
    	if(null != asignatura) {
    		Alumno alumno = alumnoRepository.findAlumnoByNombreUsuario(nombreUsuario);
    		AlumnoAsignatura alumnoAsignatura = new AlumnoAsignatura("Pendiente", 0, alumno, asignatura);
    		alumnoAsignaturaRepository.save(alumnoAsignatura);
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public List<Alumno> buscarSolicitudesPendientes(Long id){
    	List<Alumno> listaAlumnosPendientes = alumnoAsignaturaRepository.findAlumnosPendientesEnAsignatura(id);
    	return listaAlumnosPendientes;
    }
    
    public boolean aceptarRechazarAlumno(Long idAsignatura, Long idAlumno, boolean aceptado) {
    	AlumnoAsignatura alumnoAsignatura = alumnoAsignaturaRepository.findAlumnoAsignaturaByIdAlumnoIdAsignatura(idAsignatura, idAlumno);
    	if(aceptado) {
    		alumnoAsignatura.setEstado("Aceptado");
    	} else {
    		alumnoAsignatura.setEstado("Rechazado");
    	}
    	alumnoAsignaturaRepository.save(alumnoAsignatura);
    	return true;
    }
    public static String generateRandomCode() {
        String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder(7);

        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(letters.length());
            code.append(letters.charAt(index));
        }

        for (int i = 0; i < 3; i++) {
            int index = random.nextInt(numbers.length());
            code.append(numbers.charAt(index));
        }

        return code.toString();
    }

}
