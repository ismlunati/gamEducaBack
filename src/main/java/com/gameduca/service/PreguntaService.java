package com.gameduca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Pregunta;
import com.gameduca.entity.Respuesta;
import com.gameduca.entity.Tema;
import com.gameduca.repository.PreguntaRepository;
import com.gameduca.repository.RespuestaRepository;
import com.gameduca.repository.TemaRepository;

@Service
@Transactional
public class PreguntaService {
	
    @Autowired
    PreguntaRepository preguntaRepository;
    
    @Autowired
    RespuestaRepository respuestaRepository;
    
    @Autowired
    TemaRepository temaRepository;
    
    @Autowired
    AlumnoService alumnoService;
    
    public Pregunta crearPregunta(Pregunta pregunta, Long idTema, String respuestas, String respuestaCorrecta) {
    	List<Respuesta> listaRespuestas = new ArrayList<>();
    	Tema tema = temaRepository.findById(idTema).get();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	Alumno alumno = alumnoService.obtenerAlumnoPorNombre(usuario.getUsername());
    	
        String[] arrayRespuestas = respuestas.split(",");
        for (String respuesta : arrayRespuestas) {
            respuesta = respuesta.trim();
            Respuesta entidadRespuesta = new Respuesta();
            entidadRespuesta.setTexto(respuesta);
            entidadRespuesta.setEsCorrecta(false);
            respuestaRepository.save(entidadRespuesta);
            listaRespuestas.add(entidadRespuesta);
        }
        Respuesta entidadRespuestaCorrecta = new Respuesta();
        entidadRespuestaCorrecta.setTexto(respuestaCorrecta);
        entidadRespuestaCorrecta.setEsCorrecta(true);
        listaRespuestas.add(entidadRespuestaCorrecta);

    	pregunta.setAlumno(alumno);
    	pregunta.setAsignatura(tema.getAsignatura());
    	pregunta.setRespuestas(listaRespuestas);
    	pregunta.setTema(tema);
    	preguntaRepository.save(pregunta);
    	return pregunta;
    }
    
    

}
