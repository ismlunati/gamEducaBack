package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.Pregunta;
import com.gameduca.entity.TestPreguntas;


public interface TestPreguntasRepository extends CrudRepository<TestPreguntas, Long>{
	
	@Query("SELECT tp.numeroIntento FROM TestPreguntas tp WHERE tp.test.id = :testId AND tp.idAlumnoQueResponde = :alumnoId ORDER BY tp.numeroIntento DESC")
	public List<Integer> findNumeroIntentoEnTest(@Param("testId") Long testId, @Param("alumnoId") Long alumnoId);
	
	@Query("SELECT tp.pregunta FROM TestPreguntas tp WHERE tp.test.id = :testId AND tp.idAlumnoQueResponde = :alumnoId and tp.respondida = false ORDER BY tp.numeroIntento DESC, tp.id ASC")
	public List<Pregunta> findProximaPreguntaQueResponder(@Param("testId") Long testId, @Param("alumnoId") Long alumnoId);
	
	@Query("SELECT tp.pregunta FROM TestPreguntas tp WHERE tp.test.id = :testId AND tp.idAlumnoQueResponde = null and tp.numeroIntento = null and tp.respondida = false ORDER BY tp.id ASC")
	public List<Pregunta> findPreguntasElegidas(@Param("testId") Long testId);
	
	@Query("SELECT tp FROM TestPreguntas tp WHERE tp.test.id = :testId AND tp.idAlumnoQueResponde = :alumnoId and tp.respondida = false ORDER BY tp.numeroIntento DESC, tp.id ASC")
	public List<TestPreguntas> findTestUltimaPreguntaRespondida(@Param("testId") Long testId, @Param("alumnoId") Long alumnoId);
	
	@Query("SELECT tp FROM TestPreguntas tp WHERE tp.test.id = :testId AND tp.idAlumnoQueResponde = :alumnoId and tp.numeroIntento = :numeroIntento ORDER BY tp.id ASC")
	public List<TestPreguntas> findUltimoTestRespondido(@Param("testId") Long testId, @Param("alumnoId") Long alumnoId, @Param("numeroIntento") Integer numeroIntento);
}
