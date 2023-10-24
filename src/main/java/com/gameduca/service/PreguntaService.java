package com.gameduca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.EstadoReportePregunta;
import com.gameduca.entity.Pregunta;
import com.gameduca.entity.PuntuacionTest;
import com.gameduca.entity.ReportePregunta;
import com.gameduca.entity.Respuesta;
import com.gameduca.entity.RolNombre;
import com.gameduca.entity.Tema;
import com.gameduca.entity.TestPreguntas;
import com.gameduca.entity.dto.PreguntaRespuestaSeleccionadaCorrectaDTO;
import com.gameduca.repository.PreguntaRepository;
import com.gameduca.repository.PuntuacionTestRepository;
import com.gameduca.repository.ReportePreguntaRepository;
import com.gameduca.repository.RespuestaRepository;
import com.gameduca.repository.TemaRepository;
import com.gameduca.repository.TestPreguntasRepository;

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
    ReportePreguntaRepository reportePreguntaRepository;
    
    @Autowired
    TestPreguntasRepository testPreguntasRepository;
    
    @Autowired
    AlumnoService alumnoService;
    
    @Autowired
    TestService testService;
    
    @Autowired
    PuntuacionTestRepository puntuacionTestRepository;
    
    public Pregunta crearPregunta(Pregunta pregunta, Long idTema, String respuestas, String respuestaCorrecta) {
    	List<Respuesta> listaRespuestas = new ArrayList<>();
    	Tema tema = temaRepository.findById(idTema).get();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	Alumno alumno = alumnoService.obtenerAlumnoPorNombre(usuario.getUsername());
    	
    	pregunta.setAlumno(alumno);
    	pregunta.setAsignatura(tema.getAsignatura());
    	pregunta.setTema(tema);
    	preguntaRepository.save(pregunta);
    	
        String[] arrayRespuestas = respuestas.split(",");
        for (String respuesta : arrayRespuestas) {
            respuesta = respuesta.trim();
            Respuesta entidadRespuesta = new Respuesta();
            entidadRespuesta.setTexto(respuesta);
            entidadRespuesta.setEsCorrecta(false);
            entidadRespuesta.setPregunta(pregunta);
            respuestaRepository.save(entidadRespuesta);
            listaRespuestas.add(entidadRespuesta);
        }
        Respuesta entidadRespuestaCorrecta = new Respuesta();
        entidadRespuestaCorrecta.setTexto(respuestaCorrecta);
        entidadRespuestaCorrecta.setEsCorrecta(true);
        entidadRespuestaCorrecta.setPregunta(pregunta);
    	return pregunta;
    }
    
    
    public ReportePregunta crearReportarPregunta(ReportePregunta reportePregunta, Long idPregunta) {
    	Pregunta pregunta = preguntaRepository.findById(idPregunta).get();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	Alumno alumno = alumnoService.obtenerAlumnoPorNombre(usuario.getUsername());
    	reportePregunta.setAlumno(alumno);
    	reportePregunta.setEstado(EstadoReportePregunta.ENVIADO);
    	reportePregunta.setPregunta(pregunta);
    	reportePreguntaRepository.save(reportePregunta);
    	return reportePregunta;
    }
    
    public List<ReportePregunta> crearReportarPregunta(Long idAsignatura){
    	List<ReportePregunta> result = new ArrayList<>();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	String rol = usuario.getAuthorities().iterator().next().getAuthority();
    	String nombreUsuario = usuario.getUsername();
    	if(rol.equals(RolNombre.ROLE_USER.name())) {
    		result = reportePreguntaRepository.findReportePreguntaByAlumno(nombreUsuario);
    	} else {
    		result = reportePreguntaRepository.findReportePreguntaByAsignaturaId(idAsignatura);
    	}		
    	return result;
    }
    
    public ReportePregunta aceptarRechazarReportarPregunta(Long idReportePregunta, boolean aceptar) {
		ReportePregunta reportePregunta = reportePreguntaRepository.findById(idReportePregunta).get();
    	if(aceptar) {
    		reportePregunta.setEstado(EstadoReportePregunta.ACEPTADO);
    		Pregunta pregunta = reportePregunta.getPregunta();
    		pregunta.setInvalidada(true);
    		preguntaRepository.save(pregunta);
    		cambiarPuntuaciones(pregunta);
    				
    	} else {
    		reportePregunta.setEstado(EstadoReportePregunta.RECHAZADO);
    	}
    	reportePreguntaRepository.save(reportePregunta);
    	return reportePregunta;
    }
    
    private void cambiarPuntuaciones(Pregunta pregunta) {
    	List<TestPreguntas> testsDondeEstaLaPregunta = testPreguntasRepository.findTestPreguntasByPregunta(pregunta.getId());
    	for(TestPreguntas test : testsDondeEstaLaPregunta) {
    		List<TestPreguntas> testCompleto = testPreguntasRepository.findUltimoTestRespondido(test.getTest().getId(), test.getIdAlumnoQueResponde(), test.getNumeroIntento()); 		
//    		List<PuntuacionTest> puntuacionTestBorrar = puntuacionTestRepository.findPuntuacionTestByOneTestPregunta(test);
    		PuntuacionTest puntuacionTest = test.getPuntuacionTest();
    		for(TestPreguntas testEliminarPuntuacion: testCompleto) {
    			testEliminarPuntuacion.setPuntuacionTest(null);
        		testPreguntasRepository.save(testEliminarPuntuacion);
    		}
    		
    		puntuacionTestRepository.delete(puntuacionTest);
    		testCompleto.remove(test);
    		testService.calculoPuntuacion(testCompleto);
    	}
    }

}
