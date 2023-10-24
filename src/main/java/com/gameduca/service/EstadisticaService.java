package com.gameduca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gameduca.entity.Artefacto;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.EstadoArtefacto;
import com.gameduca.entity.Pregunta;
import com.gameduca.entity.Tema;
import com.gameduca.entity.Test;
import com.gameduca.entity.TestPreguntas;
import com.gameduca.entity.dto.ArtefactoDTO;
import com.gameduca.entity.dto.EstadisticasPreguntasPorAlumnosDTO;
import com.gameduca.entity.dto.EstadisticasPreguntasPorTemasDTO;
import com.gameduca.entity.dto.EstadisticasReportesAlumnosDTO;
import com.gameduca.entity.dto.EstadisticasTestPorAlumnosDTO;
import com.gameduca.entity.dto.EstadisticasTestPorTestDTO;
import com.gameduca.entity.dto.mapper.ArtefactoDTOMapper;
import com.gameduca.entity.dto.mapper.EstadisticasDTOMapper;
import com.gameduca.repository.ArtefactoRepository;
import com.gameduca.repository.AsignaturaRepository;
import com.gameduca.repository.PreguntaRepository;
import com.gameduca.repository.ReportePreguntaRepository;
import com.gameduca.repository.TestPreguntasRepository;
import com.gameduca.repository.TestRepository;

@Service
@Transactional
public class EstadisticaService {
	
    @Autowired
    private PreguntaRepository preguntaRepository;
    
    @Autowired
    private TestPreguntasRepository testPreguntasRepository;
    
    @Autowired
    private ReportePreguntaRepository reportePreguntaRepository;
    
    @Autowired
    private EstadisticasDTOMapper estadisticasDTOMapper;
    

    public List<EstadisticasPreguntasPorTemasDTO> obtenerEstadisticasPreguntasPorTemas(Long idAsignatura) {
    	List<Pregunta> listaPreguntasAsignatura = preguntaRepository.findPreguntasByAsignaturaOrderTema(idAsignatura);
    	return estadisticasDTOMapper.mapperEstadisticasPreguntasPorTemaDTO(listaPreguntasAsignatura);
    }
    
    public List<EstadisticasPreguntasPorAlumnosDTO> obtenerEstadisticasPreguntasPorAlumnos(Long idAsignatura) {
    	List<Pregunta> listaPreguntasAsignatura = preguntaRepository.findPreguntasByAsignaturaOrderAlumno(idAsignatura);
    	return estadisticasDTOMapper.mapperEstadisticasPreguntasPorAlumnosDTO(listaPreguntasAsignatura);
    }
    
    public List<EstadisticasTestPorTestDTO> obtenerEstadisticasTestPorTest(Long idAsignatura) {
    	List<TestPreguntas> listaTestAsignatura = testPreguntasRepository.findDistinctTestPreguntasByAsignaturaIdOrderTest(idAsignatura);
    	return estadisticasDTOMapper.mapperEstadisticasTestPorTestDTO(listaTestAsignatura);
    }
    
    public List<EstadisticasTestPorAlumnosDTO> obtenerEstadisticasTestPorAlumnos(Long idAsignatura) {
    	List<TestPreguntas> listaTestAsignatura = testPreguntasRepository.findDistinctTestPreguntasByAsignaturaIdOrderAlumno(idAsignatura);
    	return estadisticasDTOMapper.mapperEstadisticasTestPorAlumnosDTO(listaTestAsignatura);
    }
    
    public List<EstadisticasReportesAlumnosDTO> obtenerReportesQueHaRealizadoAlumno(Long idAsignatura) {
    	List<EstadisticasReportesAlumnosDTO> result = reportePreguntaRepository.obtenerReportesRealizadosPorAlumno(idAsignatura);
    	return result;
    }
    
    public List<EstadisticasReportesAlumnosDTO> obtenerPreguntasQueHanSidoReportadas(Long idAsignatura) {
    	List<EstadisticasReportesAlumnosDTO> result = reportePreguntaRepository.obtenerCantidadPreguntasReportadasDelAlumno(idAsignatura);
    	return result;
    }
    
}
