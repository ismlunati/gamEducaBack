package com.gameduca.entity.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoReto;
import com.gameduca.entity.Artefacto;
import com.gameduca.entity.Compra;
import com.gameduca.entity.Reto;
import com.gameduca.entity.dto.AlumnoRetoDTO;
import com.gameduca.entity.dto.ArtefactoCompraDTO;
import com.gameduca.entity.dto.RetoConEstadoDTO;

@Component
public class ArtefactoCompraDTOMapper {
	
    public List<ArtefactoCompraDTO> toDTO(List<Compra> listaCompra) {
    	List<ArtefactoCompraDTO> listaArtefactoCompraDTO = new ArrayList<>();

        for(Compra compra : listaCompra) {
        	ArtefactoCompraDTO artefactoCompraDTO = new ArtefactoCompraDTO();
        	Artefacto artefacto = new Artefacto();
        	BeanUtils.copyProperties(compra.getArtefacto(), artefacto);

        	artefactoCompraDTO.setArtefacto(artefacto);
        	artefactoCompraDTO.setEstadoDeLaCompra(compra.getEstado());
        	artefactoCompraDTO.setIdCompra(compra.getId());
        	listaArtefactoCompraDTO.add(artefactoCompraDTO);
    	}
        return listaArtefactoCompraDTO;
    }

}
