package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.Pregunta;

public interface PreguntaRepository extends CrudRepository<Pregunta, Long>{
	
	@Query("SELECT p FROM Pregunta p WHERE p.asignatura.id = :asignaturaId AND p.tema.id IN :temas")
	public List<Pregunta> findPreguntasByAsignaturaAndTemas(@Param("asignaturaId") Long asignaturaId, @Param("temas") Long[] temas);
	
	@Query("SELECT p FROM Pregunta p WHERE p.id IN :ids")
	public List<Pregunta> findPreguntasByArrayIds(@Param("ids") Long[] ids);
	

}
