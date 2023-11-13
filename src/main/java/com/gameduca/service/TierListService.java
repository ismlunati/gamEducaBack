package com.gameduca.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoAsignatura;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.EstadoAlumnoAsignatura;
import com.gameduca.entity.Pregunta;
import com.gameduca.entity.Profesor;
import com.gameduca.entity.Rol;
import com.gameduca.entity.RolNombre;
import com.gameduca.entity.Tier;
import com.gameduca.entity.TierList;
import com.gameduca.entity.dto.AlumnosAndTiersDTO;
import com.gameduca.entity.dto.EstadisticasPreguntasPorTemasDTO;
import com.gameduca.entity.dto.ListaAlumnosAndTierListDTO;
import com.gameduca.repository.AlumnoAsignaturaRepository;
import com.gameduca.repository.AlumnoRepository;
import com.gameduca.repository.AsignaturaRepository;
import com.gameduca.repository.ProfesorRepository;
import com.gameduca.repository.ReportePreguntaRepository;
import com.gameduca.repository.TierListRepository;
import com.gameduca.repository.TierRepository;

@Service
@Transactional
public class TierListService {
	
    @Autowired
    private TierListRepository tierListRepository;
    
    @Autowired
    private TierRepository tierRepository;
    
    @Autowired
    private AlumnoRepository alumnoRepository;
    
    @Autowired
    private AsignaturaService asignaturaService;
	
    public List<TierList> getTierList(Long idAsignatura) {
    	return tierListRepository.findTierListByAsignatura(idAsignatura);
    }
	
    @Transactional
    public TierList crearTierList(Long idAsignatura, TierList tierList) throws Exception {
    	tierList.setAsignatura(asignaturaService.buscarAsignaturaPorId(idAsignatura));
    	tierList = tierListRepository.save(tierList); 	
    	
    	List<Tier> listaTiers = new ArrayList<>();
    	for(Tier tier : tierList.getTiers()) {
    		tier.setTierList(tierList);
    		listaTiers.add(tier);
    	}
        tierRepository.saveAll(listaTiers); // Guarda de nuevo para persistir los Tier
        
        return tierList;
    }
    
    @Transactional
    public TierList addAlumnosATiers(Long idAsignatura, Long idTierList, AlumnosAndTiersDTO alumnosAndTiers) throws Exception {
    	List<Tier> listaTier = new ArrayList<>();
    	Map<Long, List<Long>> dicc = alumnosAndTiers.getTiers();
    	for(Long tierId : dicc.keySet()) {
    		Tier tier = tierRepository.findById(tierId).get();
    		List<Alumno> listaAlumnos = StreamSupport.stream(alumnoRepository.findAllById(dicc.get(tierId)).spliterator(), false)
                    .collect(Collectors.toList());
    		tier.setAlumnos(listaAlumnos);
    		listaTier.add(tier);
    	}
        tierRepository.saveAll(listaTier);
        return tierListRepository.findById(idTierList).get();
    }


    public ListaAlumnosAndTierListDTO getTierListAndListaAlumnos(Long idAsignatura, Long idTierList) throws Exception {
    	ListaAlumnosAndTierListDTO result = new ListaAlumnosAndTierListDTO();
    	List<Alumno> alumnos = asignaturaService.buscarAsignaturaPorId(idAsignatura).getAlumnoAsignaturas().stream()
    	                                        .map(AlumnoAsignatura::getAlumno)
    	                                        .collect(Collectors.toList());
    	TierList tierList = tierListRepository.findById(idTierList).get();
    	result.setTierList(tierList);
    	result.setListaAlumnos(alumnos);
    	return result;
    }
}
