package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.Pregunta;

public interface PreguntaRepository extends CrudRepository<Pregunta, Long>{
	
	@Query("SELECT p FROM Pregunta p WHERE p.asignatura.id = :asignaturaId AND p.tema.id IN :temas and p.invalidada = false")
	public List<Pregunta> findPreguntasByAsignaturaAndTemas(@Param("asignaturaId") Long asignaturaId, @Param("temas") Long[] temas);
	
	@Query("SELECT p FROM Pregunta p WHERE p.id IN :ids")
	public List<Pregunta> findPreguntasByArrayIds(@Param("ids") Long[] ids);
	
	@Query("SELECT p FROM Pregunta p WHERE p.asignatura.id = :asignaturaId and p.invalidada = false order by p.tema.id ASC, p.alumno.id ASC")
	public List<Pregunta> findPreguntasByAsignaturaOrderTema(@Param("asignaturaId") Long asignaturaId);
	
	@Query("SELECT p FROM Pregunta p WHERE p.asignatura.id = :asignaturaId and p.invalidada = false order by p.alumno.id ASC, p.tema.id ASC")
	public List<Pregunta> findPreguntasByAsignaturaOrderAlumno(@Param("asignaturaId") Long asignaturaId);

}
