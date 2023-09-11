package com.gameduca.entity.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoReto;
import com.gameduca.entity.ArtefactoLogro;
import com.gameduca.entity.Logro;
import com.gameduca.entity.Reto;
import com.gameduca.entity.dto.AlumnoRetoDTO;
import com.gameduca.entity.dto.LogroDTO;
import com.gameduca.entity.dto.RetoDTO;

@Component
public class AlumnoRetoDTOMapper {

//    private final RetoDTOMapper retoDTOMapper;
	@Autowired
    private LogroDTOMapper logroDTOMapper;

//    @Autowired
//    public LogroDTOMapper(RetoDTOMapper retoDTOMapper, ArtefactoLogroDTOMapper artefactoLogroDTOMapper) {
//  public LogroDTOMapper(ArtefactoLogroDTOMapper artefactoLogroDTOMapper) {
////        this.retoDTOMapper = retoDTOMapper;
//        this.artefactoLogroDTOMapper = artefactoLogroDTOMapper;
//    }

    public AlumnoRetoDTO toDTO(AlumnoReto alumnoReto) {
    	AlumnoRetoDTO dto = new AlumnoRetoDTO();
//    	dto.setAlumnoId(alumnoReto.getAlumno().ge);
//    	dto.setAutomatico(false);
//    	dto.setDescripcion(null);
//    	dto.setEstado(null);
//    	dto.setFechaFin(null);
//    	dto.setFechaInicio(null);
//    	dto.setNombre(null);
//    	dto.setNombreUsuario(null);
//    	dto.setPuntosOtorgados(null);
//    	dto.setRetoId(null);
//    	dto.setTemporal(false);
        Alumno alumno = new Alumno();
        BeanUtils.copyProperties(alumnoReto.getAlumno(), alumno);
        alumno.setAlumnoRetos(null);
        
        Reto reto = new Reto();
        BeanUtils.copyProperties(alumnoReto.getReto(), reto);
        reto.setAlumnoRetos(null);
        
        dto.setAlumno(alumno);
        dto.setReto(reto);
    	dto.setEstado(alumnoReto.getEstado());
    	dto.setAlumnoRetoId(alumnoReto.getId());
        
        return dto;
    }
    
   
}


