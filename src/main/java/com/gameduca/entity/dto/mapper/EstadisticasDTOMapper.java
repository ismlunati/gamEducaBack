package com.gameduca.entity.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoReto;
import com.gameduca.entity.ArtefactoLogro;
import com.gameduca.entity.Logro;
import com.gameduca.entity.Pregunta;
import com.gameduca.entity.Reto;
import com.gameduca.entity.Tema;
import com.gameduca.entity.Test;
import com.gameduca.entity.TestPreguntas;
import com.gameduca.entity.dto.AlumnoRetoDTO;
import com.gameduca.entity.dto.EstadisticasPreguntasPorAlumnosDTO;
import com.gameduca.entity.dto.EstadisticasPreguntasPorAlumnosDTO.EstadisticasTemasPreguntas;
import com.gameduca.entity.dto.EstadisticasPreguntasPorTemasDTO;
import com.gameduca.entity.dto.EstadisticasPreguntasPorTemasDTO.EstadisticasAlumnosPreguntas;
import com.gameduca.entity.dto.EstadisticasTestPorAlumnosDTO;
import com.gameduca.entity.dto.EstadisticasTestPorAlumnosDTO.EstadisticasTestTest;
import com.gameduca.repository.TestPreguntasRepository;
import com.gameduca.service.AlumnoService;
import com.gameduca.entity.dto.EstadisticasTestPorTestDTO;
import com.gameduca.entity.dto.EstadisticasTestPorTestDTO.EstadisticasAlumnosTest;
import com.gameduca.entity.dto.LogroDTO;
import com.gameduca.entity.dto.RetoConEstadoDTO;
import com.gameduca.entity.dto.RetoDTO;

@Component
public class EstadisticasDTOMapper {
	
    @Autowired
    private AlumnoService alumnoService;
    	
	// MAPPER DE EstadisticasPreguntasPorTemasDTO
    
    public List<EstadisticasPreguntasPorTemasDTO> mapperEstadisticasPreguntasPorTemaDTO(List<Pregunta> listaInicial){
    	int i = 0;
    	int j = 0;
    	Tema temaActual = null;
    	Alumno alumnoActual = null;
    	EstadisticasPreguntasPorTemasDTO estadisticasPreguntasPorTemasDTOActual = new EstadisticasPreguntasPorTemasDTO();
    	EstadisticasAlumnosPreguntas estadisticasAlumnosPreguntasActual = estadisticasPreguntasPorTemasDTOActual.new EstadisticasAlumnosPreguntas();
    	List<EstadisticasPreguntasPorTemasDTO> result = new ArrayList<>();
    	List<EstadisticasAlumnosPreguntas> listaEstadisticasAlumnosPreguntasActual = new ArrayList<>();

    	for(Pregunta pregunta : listaInicial) {
    		if(temaActual == null) {
    			temaActual = pregunta.getTema();
    		}
    		// Si el tema de la pregunta que se itera no es el mismo que el tema actual, se añade el tema y las estadisticas al result y 
    		// el temaActual cambia
    		if(!temaActual.equals(pregunta.getTema())) {
    			result = añadirItemListaEstadisticasPreguntasPorTemas(result, j, temaActual, listaEstadisticasAlumnosPreguntasActual, 
    					alumnoActual);
    			estadisticasPreguntasPorTemasDTOActual = new EstadisticasPreguntasPorTemasDTO();
    			listaEstadisticasAlumnosPreguntasActual = new ArrayList<>();
    			temaActual = pregunta.getTema();
    			alumnoActual = null;
    			j = 0;
    		}
    		if(alumnoActual == null) {
    			alumnoActual = pregunta.getAlumno();
    		}
			if(!alumnoActual.equals(pregunta.getAlumno())) {
				listaEstadisticasAlumnosPreguntasActual = añadirAlumnoListaEstadisticas(alumnoActual, j, estadisticasPreguntasPorTemasDTOActual, listaEstadisticasAlumnosPreguntasActual);
    			estadisticasAlumnosPreguntasActual = estadisticasPreguntasPorTemasDTOActual.new EstadisticasAlumnosPreguntas();
				alumnoActual = pregunta.getAlumno();
				j = 0;
			}
    		if(i==listaInicial.size()-1) {
    			result = añadirItemListaEstadisticasPreguntasPorTemas(result, j+1, temaActual, listaEstadisticasAlumnosPreguntasActual, 
    					alumnoActual);
    		}

			j++;
			i++;
    	}
    	return result;
    }
    
    
    private List<EstadisticasAlumnosPreguntas> añadirAlumnoListaEstadisticas(Alumno alumnoActual, int j, 
    		EstadisticasPreguntasPorTemasDTO estadisticasPreguntasPorTemasDTOActual, List<EstadisticasAlumnosPreguntas> result)  {
    	EstadisticasAlumnosPreguntas estadisticasAlumnosPreguntasActual = estadisticasPreguntasPorTemasDTOActual.new EstadisticasAlumnosPreguntas();
    	estadisticasAlumnosPreguntasActual.setNumeroDePreguntas(j);
    	Alumno alumno = new Alumno();
    	BeanUtils.copyProperties(alumnoActual, alumno);
        alumno.setAlumnoRetos(null);
        alumno.setAlumnoAsignaturas(null);
        alumno.setPreguntas(null);
		estadisticasAlumnosPreguntasActual.setAlumno(alumno);
		result.add(estadisticasAlumnosPreguntasActual);
		return result;
    }
    
    private List<EstadisticasPreguntasPorTemasDTO> añadirItemListaEstadisticasPreguntasPorTemas(List<EstadisticasPreguntasPorTemasDTO> result, int j,
    		Tema temaActual, List<EstadisticasAlumnosPreguntas> listaEstadisticasAlumnosPreguntasActual, Alumno alumnoActual){
    	EstadisticasPreguntasPorTemasDTO estadisticasPreguntasPorTemasDTOActual = new EstadisticasPreguntasPorTemasDTO();
    	Tema tema = new Tema();
    	BeanUtils.copyProperties(temaActual, tema);
        tema.setAsignatura(null);
        tema.setPreguntas(null);
		estadisticasPreguntasPorTemasDTOActual.setTema(tema);
		listaEstadisticasAlumnosPreguntasActual = añadirAlumnoListaEstadisticas(alumnoActual, j, estadisticasPreguntasPorTemasDTOActual, listaEstadisticasAlumnosPreguntasActual);
		estadisticasPreguntasPorTemasDTOActual.setEstadisticas(listaEstadisticasAlumnosPreguntasActual);
		result.add(estadisticasPreguntasPorTemasDTOActual);
    	return result;
    }
    
    // MAPPER DE EstadisticasPreguntasPorAlumnosDTO
    
    public List<EstadisticasPreguntasPorAlumnosDTO> mapperEstadisticasPreguntasPorAlumnosDTO(List<Pregunta> listaInicial){
    	int i = 0;
    	int j = 0;
    	Alumno alumnoActual = null;
    	Tema temaActual = null;
    	EstadisticasPreguntasPorAlumnosDTO estadisticasPreguntasPorAlumnoDTOActual = new EstadisticasPreguntasPorAlumnosDTO();
    	EstadisticasTemasPreguntas estadisticasTemasPreguntasActual = estadisticasPreguntasPorAlumnoDTOActual.new EstadisticasTemasPreguntas();
    	List<EstadisticasPreguntasPorAlumnosDTO> result = new ArrayList<>();
    	List<EstadisticasTemasPreguntas> listaEstadisticasTemasPreguntasActual = new ArrayList<>();

    	for(Pregunta pregunta : listaInicial) {
    		if(alumnoActual == null) {
    			alumnoActual = pregunta.getAlumno();
    		}
    		if(!alumnoActual.equals(pregunta.getAlumno())) {
    			result = añadirItemListaEstadisticasPreguntasPorAlumno(result, j, alumnoActual, listaEstadisticasTemasPreguntasActual, 
    					temaActual);
    			estadisticasPreguntasPorAlumnoDTOActual = new EstadisticasPreguntasPorAlumnosDTO();
    			listaEstadisticasTemasPreguntasActual = new ArrayList<>();
    			alumnoActual = pregunta.getAlumno();
    			temaActual = null;
    			j = 0;
    		}
    		if(temaActual == null) {
    			temaActual = pregunta.getTema();
    		}
			if(!temaActual.equals(pregunta.getTema())) {
				listaEstadisticasTemasPreguntasActual = añadirTemaListaEstadisticas(temaActual, j, estadisticasPreguntasPorAlumnoDTOActual, listaEstadisticasTemasPreguntasActual);
				estadisticasTemasPreguntasActual = estadisticasPreguntasPorAlumnoDTOActual.new EstadisticasTemasPreguntas();
				temaActual = pregunta.getTema();
				j = 0;
			}
    		if(i==listaInicial.size()-1) {
    			result = añadirItemListaEstadisticasPreguntasPorAlumno(result, j+1, alumnoActual, listaEstadisticasTemasPreguntasActual, 
    					temaActual);
    		}

			j++;
			i++;
    	}
    	return result;
    }
    
    
    private List<EstadisticasTemasPreguntas> añadirTemaListaEstadisticas(Tema temaActual, int j, 
    		EstadisticasPreguntasPorAlumnosDTO estadisticasPreguntasPorAlumnoDTOActual, List<EstadisticasTemasPreguntas> result)  {
    	EstadisticasTemasPreguntas estadisticasTemasPreguntasActual = estadisticasPreguntasPorAlumnoDTOActual.new EstadisticasTemasPreguntas();
    	estadisticasTemasPreguntasActual.setNumeroDePreguntas(j);
    	Tema tema = new Tema();
    	BeanUtils.copyProperties(temaActual, tema);
		tema.setAsignatura(null);
        tema.setPreguntas(null);

        estadisticasTemasPreguntasActual.setTema(tema);
		result.add(estadisticasTemasPreguntasActual);
		return result;
    }
    
    private List<EstadisticasPreguntasPorAlumnosDTO> añadirItemListaEstadisticasPreguntasPorAlumno(List<EstadisticasPreguntasPorAlumnosDTO> result, int j,
    		Alumno alumnoActual, List<EstadisticasTemasPreguntas> listaEstadisticasTemasPreguntasActual, Tema temaActual){
    	EstadisticasPreguntasPorAlumnosDTO estadisticasPreguntasPorAlumnoDTOActual = new EstadisticasPreguntasPorAlumnosDTO();
    	Alumno alumno = new Alumno();
    	BeanUtils.copyProperties(alumnoActual, alumno);
        alumno.setAlumnoRetos(null);
        alumno.setAlumnoAsignaturas(null);
        alumno.setPreguntas(null);
        estadisticasPreguntasPorAlumnoDTOActual.setAlumno(alumno);
        listaEstadisticasTemasPreguntasActual = añadirTemaListaEstadisticas(temaActual, j, estadisticasPreguntasPorAlumnoDTOActual, listaEstadisticasTemasPreguntasActual);
		estadisticasPreguntasPorAlumnoDTOActual.setEstadisticas(listaEstadisticasTemasPreguntasActual);
		result.add(estadisticasPreguntasPorAlumnoDTOActual);
    	return result;
    }
    
    
    // MAPPER DE EstadisticasTestPorTestDTO
    
    public List<EstadisticasTestPorTestDTO> mapperEstadisticasTestPorTestDTO(List<TestPreguntas> listaInicial){
    	int i = 0;
    	List<Double> j = new ArrayList<>();
    	Test testActual = null;
    	Long alumnoActual = null;
    	EstadisticasTestPorTestDTO estadisticasTestPorTestDTOActual = new EstadisticasTestPorTestDTO();
    	EstadisticasAlumnosTest estadisticasAlumnosTestActual = estadisticasTestPorTestDTOActual.new EstadisticasAlumnosTest();
    	List<EstadisticasTestPorTestDTO> result = new ArrayList<>();
    	List<EstadisticasAlumnosTest> listaEstadisticasAlumnosTestActual = new ArrayList<>();

    	for(TestPreguntas testPreguntas : listaInicial) {
    		if(testActual == null) {
    			testActual = testPreguntas.getTest();
    		}
    		if(!testActual.equals(testPreguntas.getTest())) {
    			result = añadirItemListaEstadisticasTestPorTest(result, j, testActual, listaEstadisticasAlumnosTestActual, 
    					alumnoActual);
    			estadisticasTestPorTestDTOActual = new EstadisticasTestPorTestDTO();
    			listaEstadisticasAlumnosTestActual = new ArrayList<>();
    			testActual = testPreguntas.getTest();
    			alumnoActual = null;
    			j = new ArrayList<>();
    		}
    		if(alumnoActual == null) {
    			alumnoActual = testPreguntas.getIdAlumnoQueResponde();
    		}
			if(!alumnoActual.equals(testPreguntas.getIdAlumnoQueResponde())) {
				listaEstadisticasAlumnosTestActual = añadirAlumnoListaEstadisticasTest(alumnoActual, j, estadisticasTestPorTestDTOActual, listaEstadisticasAlumnosTestActual);
				estadisticasAlumnosTestActual = estadisticasTestPorTestDTOActual.new EstadisticasAlumnosTest();
				alumnoActual = testPreguntas.getIdAlumnoQueResponde();
				j = new ArrayList<>();
			}
    		if(i==listaInicial.size()-1) {
    			result = añadirItemListaEstadisticasTestPorTest(result, j, testActual, listaEstadisticasAlumnosTestActual, 
    					alumnoActual);
    		}
			j.add(testPreguntas.getPuntuacionTest().getPuntuacion());
			i++;
    	}
    	return result;
    }
    
    
    private List<EstadisticasAlumnosTest> añadirAlumnoListaEstadisticasTest(Long alumnoActual, List<Double> j, 
    		EstadisticasTestPorTestDTO estadisticasTestPorTestDTOActual, List<EstadisticasAlumnosTest> result)  {
    	EstadisticasAlumnosTest estadisticasAlumnosTestActual = estadisticasTestPorTestDTOActual.new EstadisticasAlumnosTest();
    	estadisticasAlumnosTestActual.setPuntuacion(j);
    	Alumno alumnoCompleto = alumnoService.obtenerAlumnoPorId(alumnoActual);
    	Alumno alumno = new Alumno();
    	BeanUtils.copyProperties(alumnoCompleto, alumno);
        alumno.setAlumnoRetos(null);
        alumno.setAlumnoAsignaturas(null);
        alumno.setPreguntas(null);
        estadisticasAlumnosTestActual.setAlumno(alumno);
		result.add(estadisticasAlumnosTestActual);
		return result;
    }
    
    private List<EstadisticasTestPorTestDTO> añadirItemListaEstadisticasTestPorTest(List<EstadisticasTestPorTestDTO> result, List<Double> j,
    		Test testActual, List<EstadisticasAlumnosTest> listaEstadisticasAlumnosTestActual, Long alumnoActual){
    	EstadisticasTestPorTestDTO estadisticasTestPorTestDTOActual = new EstadisticasTestPorTestDTO();
    	Test test = new Test();
    	BeanUtils.copyProperties(testActual, test);
    	test.setAsignatura(null);
    	test.setTestPreguntas(null);
    	estadisticasTestPorTestDTOActual.setTest(test);
    	listaEstadisticasAlumnosTestActual = añadirAlumnoListaEstadisticasTest(alumnoActual, j, estadisticasTestPorTestDTOActual, listaEstadisticasAlumnosTestActual);
    	estadisticasTestPorTestDTOActual.setEstadisticas(listaEstadisticasAlumnosTestActual);
		result.add(estadisticasTestPorTestDTOActual);
    	return result;
    }
    
    
    // MAPPER DE EstadisticasTestPorAlumnosDTO
    
    public List<EstadisticasTestPorAlumnosDTO> mapperEstadisticasTestPorAlumnosDTO(List<TestPreguntas> listaInicial){
    	int i = 0;
    	List<Double> j = new ArrayList<>();
    	Long alumnoActual = null;
    	Test testActual = null;
    	EstadisticasTestPorAlumnosDTO estadisticasTestPorAlumnosDTOActual = new EstadisticasTestPorAlumnosDTO();
    	EstadisticasTestTest estadisticasTestTestActual = estadisticasTestPorAlumnosDTOActual.new EstadisticasTestTest();
    	List<EstadisticasTestPorAlumnosDTO> result = new ArrayList<>();
    	List<EstadisticasTestTest> listaEstadisticasTestTestActual = new ArrayList<>();

    	for(TestPreguntas testPreguntas : listaInicial) {
    		if(alumnoActual == null) {
    			alumnoActual = testPreguntas.getIdAlumnoQueResponde();
    		}
    		if(!alumnoActual.equals(testPreguntas.getIdAlumnoQueResponde())) {
    			result = añadirItemListaEstadisticasTestPorAlumnos(result, j, alumnoActual, listaEstadisticasTestTestActual, 
    					testActual);
    			estadisticasTestPorAlumnosDTOActual = new EstadisticasTestPorAlumnosDTO();
    			listaEstadisticasTestTestActual = new ArrayList<>();
    			alumnoActual = testPreguntas.getIdAlumnoQueResponde();
    			testActual = null;
    			j = new ArrayList<>();
    		}
    		if(testActual == null) {
    			testActual = testPreguntas.getTest();
    		}
			if(!testActual.equals(testPreguntas.getTest())) {
				listaEstadisticasTestTestActual = añadirTestListaEstadisticasAlumnoTest(testActual, j, estadisticasTestPorAlumnosDTOActual, listaEstadisticasTestTestActual);
				estadisticasTestTestActual = estadisticasTestPorAlumnosDTOActual.new EstadisticasTestTest();
    			testActual = testPreguntas.getTest();
				j = new ArrayList<>();
			}
    		if(i==listaInicial.size()-1) {
    			result = añadirItemListaEstadisticasTestPorAlumnos(result, j, alumnoActual, listaEstadisticasTestTestActual, 
    					testActual);
    		}
			j.add(testPreguntas.getPuntuacionTest().getPuntuacion());
			i++;
    	}
    	return result;
    }
    
    
    private List<EstadisticasTestTest> añadirTestListaEstadisticasAlumnoTest(Test testActual, List<Double> j, 
    		EstadisticasTestPorAlumnosDTO estadisticasTestPorAlumnoDTOActual, List<EstadisticasTestTest> result)  {
    	EstadisticasTestTest estadisticasTestTestActual = estadisticasTestPorAlumnoDTOActual.new EstadisticasTestTest();
    	estadisticasTestTestActual.setPuntuacion(j);
    	Test test = new Test();
    	BeanUtils.copyProperties(testActual, test);
    	test.setAsignatura(null);
    	test.setTestPreguntas(null);
    	estadisticasTestTestActual.setTest(test);
		result.add(estadisticasTestTestActual);
		return result;
    }
    
    private List<EstadisticasTestPorAlumnosDTO> añadirItemListaEstadisticasTestPorAlumnos(List<EstadisticasTestPorAlumnosDTO> result, List<Double> j,
    		Long alumnoActual, List<EstadisticasTestTest> listaEstadisticasTestTestActual, Test testActual){
    	EstadisticasTestPorAlumnosDTO estadisticasTestPorTestDTOActual = new EstadisticasTestPorAlumnosDTO();
    	Alumno alumnoCompleto = alumnoService.obtenerAlumnoPorId(alumnoActual);
    	Alumno alumno = new Alumno();
    	BeanUtils.copyProperties(alumnoCompleto, alumno);
        alumno.setAlumnoRetos(null);
        alumno.setAlumnoAsignaturas(null);
        alumno.setPreguntas(null);
    	estadisticasTestPorTestDTOActual.setAlumno(alumno);
    	listaEstadisticasTestTestActual = añadirTestListaEstadisticasAlumnoTest(testActual, j, estadisticasTestPorTestDTOActual, listaEstadisticasTestTestActual);
    	estadisticasTestPorTestDTOActual.setEstadisticas(listaEstadisticasTestTestActual);
		result.add(estadisticasTestPorTestDTOActual);
    	return result;
    }
    
}


