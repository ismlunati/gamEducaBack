package com.gameduca.entity.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gameduca.entity.ArtefactoLogro;
import com.gameduca.entity.Logro;
import com.gameduca.entity.dto.LogroDTO;

@Component
public class LogroDTOMapper {

//    private final RetoDTOMapper retoDTOMapper;
	@Autowired
    private ArtefactoLogroDTOMapper artefactoLogroDTOMapper;
	
	@Autowired
    private RetoDTOMapper retoDTOMapper;
//    @Autowired
//    public LogroDTOMapper(RetoDTOMapper retoDTOMapper, ArtefactoLogroDTOMapper artefactoLogroDTOMapper) {
//  public LogroDTOMapper(ArtefactoLogroDTOMapper artefactoLogroDTOMapper) {
////        this.retoDTOMapper = retoDTOMapper;
//        this.artefactoLogroDTOMapper = artefactoLogroDTOMapper;
//    }

    public LogroDTO toDTO(Logro logro) {
        LogroDTO dto = new LogroDTO();
        dto.setId(logro.getId());
        dto.setNombre(logro.getNombre());
        dto.setDescripcion(logro.getDescripcion());
        dto.setImagen(logro.getImagen());
        dto.setRetos(logro.getRetos().stream().map(reto -> retoDTOMapper.toLogroDTO(reto)).collect(Collectors.toList()));
//        dto.setArtefactoLogros(logro.getArtefactoLogros().stream().map(artefactoLogro -> artefactoLogroDTOMapper.toDTOLogro(artefactoLogro)).collect(Collectors.toList()));
        if(logro.getArtefactoLogros()!= null) {
            dto.setArtefactoLogros(artefactoLogroDTOMapper.toDTOLogro(logro.getArtefactoLogros()));
        }
        return dto;
    }
    
    public LogroDTO toRetoDTO(Logro logro) {
        LogroDTO dto = new LogroDTO();
        dto.setId(logro.getId());
        dto.setNombre(logro.getNombre());
        dto.setDescripcion(logro.getDescripcion());
        dto.setImagen(logro.getImagen());
//        dto.setRetos(logro.getRetos().stream().map(reto -> retoDTOMapper.toLogroDTO(reto)).collect(Collectors.toList()));
        dto.setArtefactoLogros(artefactoLogroDTOMapper.toDTOLogro(logro.getArtefactoLogros()));
        return dto;
    }
    
    
    public LogroDTO artefactoToDTO(Logro logro) {
        LogroDTO dto = new LogroDTO();
        dto.setId(logro.getId());
        dto.setNombre(logro.getNombre());
        dto.setDescripcion(logro.getDescripcion());
        dto.setImagen(logro.getImagen());
//        dto.setRetos(logro.getRetos().stream().map(reto -> retoDTOMapper.toDTO(reto)).collect(Collectors.toList()));
//        dto.setArtefactoLogros(logro.getArtefactoLogros().stream().map(artefactoLogro -> artefactoLogroDTOMapper.toDTO(artefactoLogro)).collect(Collectors.toList()));
        return dto;
    }

    public Logro toEntity(LogroDTO dto) {
        Logro logro = new Logro();
        logro.setId(dto.getId());
        logro.setNombre(dto.getNombre());
        logro.setDescripcion(dto.getDescripcion());
        logro.setImagen(dto.getImagen());
//        logro.setRetos(dto.getRetos().stream().map(retoDTO -> retoDTOMapper.toEntity(retoDTO)).collect(Collectors.toList()));
        
//        List<ArtefactoLogro> artefactoLogros = dto.getArtefactoLogros().stream()
//            .map(artefactoLogroDTO -> artefactoLogroDTOMapper.toEntity(artefactoLogroDTO))
//            .collect(Collectors.toList());
//        artefactoLogros.forEach(artefactoLogro -> artefactoLogro.setLogro(logro));
//        logro.setArtefactoLogros(artefactoLogros);
        
        return logro;
    }
}

