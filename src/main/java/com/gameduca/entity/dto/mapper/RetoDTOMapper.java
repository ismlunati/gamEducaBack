package com.gameduca.entity.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gameduca.entity.ArtefactoLogro;
import com.gameduca.entity.Logro;
import com.gameduca.entity.Reto;
import com.gameduca.entity.dto.LogroDTO;
import com.gameduca.entity.dto.RetoDTO;

@Component
public class RetoDTOMapper {

//    private final RetoDTOMapper retoDTOMapper;
	@Autowired
    private LogroDTOMapper logroDTOMapper;

//    @Autowired
//    public LogroDTOMapper(RetoDTOMapper retoDTOMapper, ArtefactoLogroDTOMapper artefactoLogroDTOMapper) {
//  public LogroDTOMapper(ArtefactoLogroDTOMapper artefactoLogroDTOMapper) {
////        this.retoDTOMapper = retoDTOMapper;
//        this.artefactoLogroDTOMapper = artefactoLogroDTOMapper;
//    }

    public RetoDTO toDTO(Reto reto) {
        RetoDTO dto = new RetoDTO();
        dto.setId(reto.getId());
        dto.setNombre(reto.getNombre());
        dto.setDescripcion(reto.getDescripcion());
        dto.setAutomatico(reto.isAutomatico());
        dto.setFechaInicio(reto.getFechaInicio());
        dto.setFechaFin(reto.getFechaFin());
        dto.setPuntosOtorgados(reto.getPuntosOtorgados());
        dto.setTemporal(reto.isTemporal());
        dto.setLogro(logroDTOMapper.toRetoDTO(reto.getLogro()));
        return dto;
    }
    
    public RetoDTO toLogroDTO(Reto reto) {
        RetoDTO dto = new RetoDTO();
        dto.setId(reto.getId());
        dto.setNombre(reto.getNombre());
        dto.setDescripcion(reto.getDescripcion());
        dto.setAutomatico(reto.isAutomatico());
        dto.setFechaInicio(reto.getFechaInicio());
        dto.setFechaFin(reto.getFechaFin());
        dto.setPuntosOtorgados(reto.getPuntosOtorgados());
        dto.setTemporal(reto.isTemporal());
//        dto.setLogro(logroDTOMapper.toDTO(reto.getLogro()));
        return dto;
    }

//    public Logro toEntity(LogroDTO dto) {
//        Logro logro = new Logro();
//        logro.setId(dto.getId());
//        logro.setNombre(dto.getNombre());
//        logro.setDescripcion(dto.getDescripcion());
//        logro.setImagen(dto.getImagen());
//        return logro;
//    }
}


