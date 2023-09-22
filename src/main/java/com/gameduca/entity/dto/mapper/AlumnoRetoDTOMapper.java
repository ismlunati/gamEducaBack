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
import com.gameduca.entity.Reto;
import com.gameduca.entity.dto.AlumnoRetoDTO;
import com.gameduca.entity.dto.LogroDTO;
import com.gameduca.entity.dto.RetoConEstadoDTO;
import com.gameduca.entity.dto.RetoDTO;

@Component
public class AlumnoRetoDTOMapper {


    public List<AlumnoRetoDTO> toDTO(List<AlumnoReto> listaAlumnoReto) {
    	List<AlumnoRetoDTO> listaAlumnoRetoDTO = new ArrayList<>();
        List<RetoConEstadoDTO> listaRetoConEstado = new ArrayList<>();
        Alumno ultimoAlumno = null;
        int i = 0;
        for(AlumnoReto alumnoReto : listaAlumnoReto) {
            Alumno alumnoDeAlumnoReto = alumnoReto.getAlumno();
            if(ultimoAlumno == null) {
            	ultimoAlumno = alumnoDeAlumnoReto;
            }
        	if(!alumnoDeAlumnoReto.getId().equals(ultimoAlumno.getId())) {
        		listaAlumnoRetoDTO = añadiraLaLista(ultimoAlumno, listaRetoConEstado, listaAlumnoRetoDTO);
        		ultimoAlumno = alumnoDeAlumnoReto;
                listaRetoConEstado = new ArrayList<>();
        	}
        	Alumno alumno = new Alumno();
        	BeanUtils.copyProperties(alumnoDeAlumnoReto, alumno);
            alumno.setAlumnoRetos(null);
            alumno.setAlumnoAsignaturas(null);
            alumno.setPreguntas(null);
            
            RetoConEstadoDTO retoConEstado = new RetoConEstadoDTO();
            Reto reto = new Reto();
            BeanUtils.copyProperties(alumnoReto.getReto(), reto);
			reto.setAlumnoRetos(null);

			retoConEstado.setReto(reto);
			retoConEstado.setEstado(alumnoReto.getEstado());
			retoConEstado.setIdAlumnoReto(alumnoReto.getId());
			listaRetoConEstado.add(retoConEstado);
			ultimoAlumno = alumno;
			i++;
            if(i == listaAlumnoReto.size()) {
        		listaAlumnoRetoDTO = añadiraLaLista(alumno, listaRetoConEstado, listaAlumnoRetoDTO);
            }
    	}
        return listaAlumnoRetoDTO;
    }
    
    public List<AlumnoRetoDTO> añadiraLaLista(Alumno alumno, List<RetoConEstadoDTO> listaRetoConEstado, List<AlumnoRetoDTO> listaAlumnoRetoDTO){
    	AlumnoRetoDTO dto = new AlumnoRetoDTO();
        dto.setAlumno(alumno);
        dto.setRetoConEstado(listaRetoConEstado);
        listaAlumnoRetoDTO.add(dto);
    	return listaAlumnoRetoDTO;
    }
    
//    public AlumnoRetoDTO toDTO(List<AlumnoReto> listaAlumnoReto) {
//    	AlumnoRetoDTO dto = new AlumnoRetoDTO();
//		Alumno alumno = new Alumno();
//        List<RetoConEstadoDTO> listaRetoConEstado = new ArrayList<>();
//        Long idUltimoAlumno = null;
//
//    	if(!listaAlumnoReto.isEmpty()) {
//            BeanUtils.copyProperties(listaAlumnoReto.get(0).getAlumno().get, alumno);
//            alumno.setAlumnoRetos(null);
//            alumno.setAlumnoAsignaturas(null);
//            alumno.setPreguntas(null);
//            
//            for(AlumnoReto alumnoReto : listaAlumnoReto) {
//            	RetoConEstadoDTO retoConEstado = new RetoConEstadoDTO();
//                Reto reto = new Reto();
//                BeanUtils.copyProperties(alumnoReto.getReto(), reto);
//                reto.setAlumnoRetos(null);
//                
//                retoConEstado.setReto(reto);
//                retoConEstado.setEstado(alumnoReto.getEstado());
//                listaRetoConEstado.add(retoConEstado);
//            }       
//    	}
//        dto.setAlumno(alumno);
//        dto.setRetoConEstado(listaRetoConEstado);
//        
//        return dto;
//    }
   
}


