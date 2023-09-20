package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.Respuesta;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long>{
	
	@Query("SELECT r FROM Respuesta r WHERE r.esCorrecta = true AND r.pregunta.id = :preguntaId")
	public Respuesta findIdRespuestaCorrectaByPreguntaId(@Param("preguntaId") Long preguntaId);

}
