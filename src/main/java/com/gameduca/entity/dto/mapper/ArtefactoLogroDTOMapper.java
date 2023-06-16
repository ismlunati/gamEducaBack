package com.gameduca.entity.dto.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gameduca.entity.Artefacto;
import com.gameduca.entity.ArtefactoLogro;
import com.gameduca.entity.Logro;
import com.gameduca.entity.dto.ArtefactoDTO;
import com.gameduca.entity.dto.ArtefactoLogroDTO;
import com.gameduca.entity.dto.LogroDTO;
import com.gameduca.repository.ArtefactoRepository;
import com.gameduca.repository.LogroRepository;

@Component
public class ArtefactoLogroDTOMapper {
	@Autowired
	private ArtefactoDTOMapper artefactoDTOMapper;
	@Autowired
	private LogroDTOMapper logroDTOMapper;
	
	@Autowired
	ArtefactoRepository artefactoRepository;
	
	@Autowired
	LogroRepository logroRepository;


//    @Autowired
//    public ArtefactoLogroDTOMapper(ArtefactoDTOMapper artefactoDTOMapper, LogroDTOMapper logroDTOMapper) {
//        this.artefactoDTOMapper = artefactoDTOMapper;
//        this.logroDTOMapper = logroDTOMapper;
//    }

    public ArtefactoLogroDTO toDTOArtefacto(ArtefactoLogro artefactoLogro) {
        ArtefactoLogroDTO dto = new ArtefactoLogroDTO();
        dto.setId(artefactoLogro.getId());
        dto.setDesbloquear(artefactoLogro.isDesbloquear());
        dto.setObtener(artefactoLogro.isObtener());

        // Suponemos que tienes un DTO para Artefacto y Logro, y mappers para ellos
//        ArtefactoDTO artefactoDTO = artefactoDTOMapper.toDTO(artefactoLogro.getArtefacto());
        LogroDTO logroDTO = logroDTOMapper.artefactoToDTO(artefactoLogro.getLogro());
        
//        dto.setArtefacto(artefactoDTO);
        dto.setLogro(logroDTO);

        return dto;
    }
    
    public ArtefactoLogroDTO toDTOLogro(ArtefactoLogro artefactoLogro) {
        ArtefactoLogroDTO dto = new ArtefactoLogroDTO();
        dto.setId(artefactoLogro.getId());
        dto.setDesbloquear(artefactoLogro.isDesbloquear());
        dto.setObtener(artefactoLogro.isObtener());

        // Suponemos que tienes un DTO para Artefacto y Logro, y mappers para ellos
        ArtefactoDTO artefactoDTO = artefactoDTOMapper.logroToDTO(artefactoLogro.getArtefacto());
//        LogroDTO logroDTO = logroDTOMapper.artefactoToDTO(artefactoLogro.getLogro());
        
        dto.setArtefacto(artefactoDTO);
//        dto.setLogro(logroDTO);

        return dto;
    }
    
    public ArtefactoLogroDTO toDTO(ArtefactoLogro artefactoLogro) {
        ArtefactoLogroDTO dto = new ArtefactoLogroDTO();
        dto.setId(artefactoLogro.getId());
        dto.setDesbloquear(artefactoLogro.isDesbloquear());
        dto.setObtener(artefactoLogro.isObtener());

        // Suponemos que tienes un DTO para Artefacto y Logro, y mappers para ellos
//        ArtefactoDTO artefactoDTO = artefactoDTOMapper.toDTO(artefactoLogro.getArtefacto());
//        LogroDTO logroDTO = logroDTOMapper.artefactoToDTO(artefactoLogro.getLogro());
        
//        dto.setArtefacto(artefactoDTO);
//        dto.setLogro(logroDTO);

        return dto;
    }

    public ArtefactoLogro toEntity(ArtefactoLogroDTO dto) {
        ArtefactoLogro artefactoLogro = new ArtefactoLogro();
        artefactoLogro.setId(dto.getId());
        artefactoLogro.setDesbloquear(dto.isDesbloquear());
        artefactoLogro.setObtener(dto.isObtener());

        // Deberías buscar el Artefacto y Logro en la base de datos
        Artefacto artefacto = null;
		try {
			artefacto = artefactoRepository.findById(dto.getArtefacto().getId())
			        .orElseThrow(() -> new Exception("No se encontró el Artefacto con ID: " + dto.getArtefacto().getId()));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        Logro logro = null;
		try {
			logro = logroRepository.findById(dto.getLogro().getId())
			        .orElseThrow(() -> new Exception("No se encontró el Logro con ID: " + dto.getLogro().getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        artefactoLogro.setArtefacto(artefacto);
        artefactoLogro.setLogro(logro);

        return artefactoLogro;
    }
}

