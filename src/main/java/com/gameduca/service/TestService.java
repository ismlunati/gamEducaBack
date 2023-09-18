package com.gameduca.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Pregunta;
import com.gameduca.entity.Respuesta;
import com.gameduca.entity.Test;
import com.gameduca.entity.TestPreguntas;
import com.gameduca.entity.Usuario;
import com.gameduca.entity.dto.PreguntaElegibleDTO;
import com.gameduca.entity.dto.ResultadoTestDTO;
import com.gameduca.repository.PreguntaRepository;
import com.gameduca.repository.RespuestaRepository;
import com.gameduca.repository.TestPreguntasRepository;
import com.gameduca.repository.TestRepository;

@Service
@Transactional
public class TestService {
	
    @Autowired
    TestRepository testRepository;
    
    @Autowired
    AsignaturaService asignaturaService;
    
    @Autowired
    AlumnoService alumnoService;
    
    @Autowired
    PreguntaRepository preguntaRepository;
    
    @Autowired
    RespuestaRepository respuestaRepository;
    
    @Autowired
    TestPreguntasRepository testPreguntasRepository;
    
    public Test crearTest(Test test, Long idAsignatura, String idPreguntasSeleccionadas) throws Exception {
    	List<Pregunta> preguntasElegidas = new ArrayList<>();
		Asignatura asignatura = asignaturaService.buscarAsignaturaPorId(idAsignatura);
    	test.setAsignatura(asignatura);
    	Test savedTest = null;
    	if(test.isPreguntasElegibles()) {
        	Long[] preguntasIdLongArray = stringToLongArray(idPreguntasSeleccionadas);
        	preguntasElegidas = preguntaRepository.findPreguntasByArrayIds(preguntasIdLongArray);
        	if(preguntasElegidas.size() == test.getNumeroPreguntas()) {
                savedTest = testRepository.save(test);
            	crearTestAuxiliar(savedTest, preguntasElegidas, null);
        	}
    	}
    	return savedTest;
    }
    
    public void crearTestAuxiliar(Test test, List<Pregunta> listaPreguntas, Long alumnoId) {
    	// Crear Test_Preguntas
        List<TestPreguntas> testPreguntasList = new ArrayList<>();
        Integer numeroIntento = null;
        for (int i = 0; i < test.getNumeroPreguntas() && i < listaPreguntas.size(); i++) {
        	TestPreguntas testPregunta = new TestPreguntas();
            testPregunta.setTest(test);
            testPregunta.setPregunta(listaPreguntas.get(i));
            testPregunta.setRespondida(false);
            testPregunta.setIdAlumnoQueResponde(alumnoId);
            testPregunta.setIdRespuestaSeleccionada(null);
            if(null != alumnoId) {
            	List<Integer> listaNumeroIntento = testPreguntasRepository.findNumeroIntentoEnTest(test.getId(), alumnoId);
            	if(!listaNumeroIntento.isEmpty()) {
            		numeroIntento = listaNumeroIntento.get(0) + 1;
            	} else {
            		numeroIntento = 1;
            	}
            }    
            testPregunta.setNumeroIntento(numeroIntento);
            testPreguntasList.add(testPregunta);
        }
        testPreguntasRepository.saveAll(testPreguntasList); 
    }
    
    public List<PreguntaElegibleDTO> getPreguntasElegibles(Long idAsignatura, String listaTemas){
    	List<Pregunta> preguntas = auxiliarPreguntasElegibles(idAsignatura, listaTemas);
        return preguntas.stream().map(pregunta -> {
        	return new PreguntaElegibleDTO(pregunta.getId(), pregunta.getEnunciado());
        }).collect(Collectors.toList());
    }
    
    public List<Pregunta> auxiliarPreguntasElegibles(Long idAsignatura, String listaTemas){
    	Long[] temasLongArray = stringToLongArray(listaTemas);
        return preguntaRepository.findPreguntasByAsignaturaAndTemas(idAsignatura, temasLongArray);

    }
    
    public Long[] stringToLongArray(String cadena) {
    	String[] stringArray = cadena.split(",");
    	Long[] longArray = new Long[stringArray.length];

    	for (int i = 0; i < stringArray.length; i++) {
    		stringArray[i] = stringArray[i].trim(); // Eliminar espacios en blanco
    		longArray[i] = Long.valueOf(stringArray[i]); // Convertir a Long
    	}
    	return longArray;
    	
    }

    public List<Test> obtenerTest(Long idAsignatura){
    	List<Test> lista = testRepository.findTestVisiblesByAsignaturaId(idAsignatura);
    	System.out.println(lista.size());
    	return lista;
    }
    
    public Pregunta realizarTest(Long idAsignatura, Long idTest, boolean inicio, Long idRespuesta) {
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	Alumno alumno = alumnoService.obtenerAlumnoPorNombre(usuario.getUsername());
    	List<Pregunta> preguntasTest = new ArrayList<>();
    	if(inicio) {
    		Test test = testRepository.findById(idTest).get();
    		if(test.isPreguntasElegibles()) {
    			// Preguntas elegidas
    			List<Pregunta> preguntasElegidas = new ArrayList<>();
    			preguntasElegidas = testPreguntasRepository.findPreguntasElegidas(idTest);
            	crearTestAuxiliar(test, preguntasElegidas, alumno.getId());
    			
    		} else {
    			// Preguntas Aleatorias
    	    	List<Pregunta> preguntasElegidas = new ArrayList<>();
                // Obtener preguntas que cumplen las restricciones
            	preguntasElegidas = auxiliarPreguntasElegibles(test.getAsignatura().getId(), test.getListaTemas());


                if(preguntasElegidas.size()>=test.getNumeroPreguntas()) {
                	// Mezclar las preguntas para selección aleatoria
                    Collections.shuffle(preguntasElegidas, new Random());               
                	crearTestAuxiliar(test, preguntasElegidas, alumno.getId());
                }
    		}
        	//Pillar primera pregunta con respondida = false ordenado por id asc
        	preguntasTest = testPreguntasRepository.findProximaPreguntaQueResponder(idTest, alumno.getId());

    	} else {
    		// responder pregunta anterior
    		System.out.println("IdTest: " + idTest);
    		System.out.println("IdAlumno: " + alumno.getId());

        	List<TestPreguntas> listaPreguntaRespondida = testPreguntasRepository.findTestUltimaPreguntaRespondida(idTest, alumno.getId());
        	System.out.println("Tamaño lista" + listaPreguntaRespondida.size());
        	TestPreguntas testPreguntaRespondida = listaPreguntaRespondida.get(0);
        	System.out.println("idRespuesta" + idRespuesta);
        	testPreguntaRespondida.setIdRespuestaSeleccionada(idRespuesta);
        	testPreguntaRespondida.setRespondida(true);
        	testPreguntasRepository.save(testPreguntaRespondida);
            	if(1 == listaPreguntaRespondida.size()) {
                	// Se acabaron las preguntas
            		System.out.println("entra");
            		Pregunta pregunta = new Pregunta();
            		pregunta.setId((long) -1);
            		System.out.println(pregunta.getId());
            		return pregunta;
            	}
    		// Pillar test, alumno, ultimo intento y primera pregunta con respondida = false ordenado por id asc
        	preguntasTest = testPreguntasRepository.findProximaPreguntaQueResponder(idTest, alumno.getId());

    	}
    	Pregunta pregunta = null;
    	if(!preguntasTest.isEmpty()) {
    		pregunta = preguntasTest.get(0);
    	}
    	return pregunta;    
    }
    
    public ResultadoTestDTO resultado(Long idAsignatura, Long idTest) {
    	ResultadoTestDTO result = new ResultadoTestDTO();
    	UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
    	Alumno alumno = alumnoService.obtenerAlumnoPorNombre(usuario.getUsername());
    	List<Integer> listaNumeroIntento = testPreguntasRepository.findNumeroIntentoEnTest(idTest, alumno.getId());
    	Integer numeroIntento = listaNumeroIntento.get(0);
    	List<TestPreguntas> listaTestPreguntasRespondidas = testPreguntasRepository.findUltimoTestRespondido(idTest, idAsignatura, numeroIntento);
    	Test test = testRepository.findById(idTest).get();
    	Integer preguntasAcertadas = 0;
    	for(TestPreguntas testPreguntas : listaTestPreguntasRespondidas) {
    		Long idRespuestaSeleccionada = testPreguntas.getIdRespuestaSeleccionada();
    		Long idRespuestaCorrecta = respuestaRepository.findIdRespuestaCorrectaByPreguntaId(testPreguntas.getPregunta().getId());
    		if(idRespuestaSeleccionada.equals(idRespuestaCorrecta)) {
    			preguntasAcertadas++;
    		}
    	}
    	result.setNumeroPreguntasTotales(test.getNumeroPreguntas());
    	result.setNumeroPreguntasAcertadas(preguntasAcertadas);
//    	result.setPuntuacion();
//    	result.setPreguntasRespondidas();
//    	result.setRespuestas();
    	return result;
    	
    	
    }
    
    
}

