package com.gameduca.entity.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gameduca.entity.Artefacto;
import com.gameduca.entity.ArtefactoLogro;
import com.gameduca.entity.dto.ArtefactoDTO;
import com.gameduca.entity.dto.ArtefactoLogroDTO;

@Component
public class ArtefactoDTOMapper {
	
	@Autowired
    private ArtefactoLogroDTOMapper artefactoLogroDTOMapper;

//    @Autowired
//    public ArtefactoDTOMapper(ArtefactoLogroDTOMapper artefactoLogroDTOMapper) {
//        this.artefactoLogroDTOMapper = artefactoLogroDTOMapper;
//    }

    public ArtefactoDTO toDTO(Artefacto artefacto) {
        ArtefactoDTO dto = new ArtefactoDTO();
        dto.setId(artefacto.getId());
        dto.setNombre(artefacto.getNombre());
        dto.setDescripcion(artefacto.getDescripcion());
        dto.setCostePuntos(artefacto.getCostePuntos());
        dto.setEstado(artefacto.getEstado());
        dto.setTemporal(artefacto.isTemporal());
        dto.setFechaInicio(artefacto.getFechaInicio());
        dto.setFechaFin(artefacto.getFechaFin());
        dto.setRepetible(artefacto.isRepetible());

        // Mapeo de las relaciones
//        AsignaturaDTO asignaturaDTO = asignaturaMapper.toDTO(artefacto.getAsignatura());
//        dto.setAsignatura(asignaturaDTO);

//        List<CompraDTO> comprasDTO = artefacto.getCompras().stream()
//            .map(compra -> compraMapper.toDTO(compra))
//            .collect(Collectors.toList());
//        dto.setCompras(comprasDTO);

        List<ArtefactoLogroDTO> artefactoLogrosDTO = artefacto.getArtefactoLogros().stream()
            .map(artefactoLogro -> artefactoLogroDTOMapper.toDTOArtefacto(artefactoLogro))
            .collect(Collectors.toList());
        dto.setArtefactoLogros(artefactoLogrosDTO);

        return dto;
    }

    
    public ArtefactoDTO logroToDTO(Artefacto artefacto) {
        ArtefactoDTO dto = new ArtefactoDTO();
        dto.setId(artefacto.getId());
        dto.setNombre(artefacto.getNombre());
        dto.setDescripcion(artefacto.getDescripcion());
        dto.setCostePuntos(artefacto.getCostePuntos());
        dto.setEstado(artefacto.getEstado());
        dto.setTemporal(artefacto.isTemporal());
        dto.setFechaInicio(artefacto.getFechaInicio());
        dto.setFechaFin(artefacto.getFechaFin());
        dto.setRepetible(artefacto.isRepetible());

        // Mapeo de las relaciones
//        AsignaturaDTO asignaturaDTO = asignaturaMapper.toDTO(artefacto.getAsignatura());
//        dto.setAsignatura(asignaturaDTO);

//        List<CompraDTO> comprasDTO = artefacto.getCompras().stream()
//            .map(compra -> compraMapper.toDTO(compra))
//            .collect(Collectors.toList());
//        dto.setCompras(comprasDTO);

//        List<ArtefactoLogroDTO> artefactoLogrosDTO = artefacto.getArtefactoLogros().stream()
//            .map(artefactoLogro -> artefactoLogroDTOMapper.toDTOArtefacto(artefactoLogro))
//            .collect(Collectors.toList());
//        dto.setArtefactoLogros(artefactoLogrosDTO);

        return dto;
    }
    
    
    public Artefacto toEntity(ArtefactoDTO dto) {
        Artefacto artefacto = new Artefacto();
        artefacto.setId(dto.getId());
        artefacto.setNombre(dto.getNombre());
        artefacto.setDescripcion(dto.getDescripcion());
        artefacto.setCostePuntos(dto.getCostePuntos());
        artefacto.setEstado(dto.getEstado());
        artefacto.setTemporal(dto.isTemporal());
        artefacto.setFechaInicio(dto.getFechaInicio());
        artefacto.setFechaFin(dto.getFechaFin());
        artefacto.setRepetible(dto.isRepetible());

        // Deberías buscar la Asignatura en la base de datos
//        Asignatura asignatura = asignaturaRepository.findById(dto.getAsignatura().getId())
//                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la Asignatura con ID: " + dto.getAsignatura().getId()));
//        artefacto.setAsignatura(asignatura);

        // Ahora mapeamos la lista de ArtefactoLogroDTO a ArtefactoLogro
        List<ArtefactoLogro> artefactoLogros = dto.getArtefactoLogros().stream()
                .map(artefactoLogroDTO -> artefactoLogroDTOMapper.toEntity(artefactoLogroDTO))
                .collect(Collectors.toList());

        // Antes de asignar la lista de ArtefactoLogro al artefacto, necesitamos garantizar que cada ArtefactoLogro en la lista tiene una referencia correcta a este artefacto
        artefactoLogros.forEach(artefactoLogro -> artefactoLogro.setArtefacto(artefacto));

        artefacto.setArtefactoLogros(artefactoLogros);

        return artefacto;
    }

}

