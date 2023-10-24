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

	@Query("SELECT tp FROM TestPreguntas tp WHERE tp.pregunta.id = :preguntaId")
	public List<TestPreguntas> findTestPreguntasByPregunta(@Param("preguntaId") Long preguntaId);
	
	@Query("FROM TestPreguntas tp WHERE tp.id IN (" +
		       "    SELECT MIN(subTp.id) " +
		       "    FROM TestPreguntas subTp " +
		       "    WHERE subTp.test.asignatura.id = :asignaturaId " +
		       "    GROUP BY subTp.puntuacionTest.id" +
		       ") order by tp.test.id asc, tp.idAlumnoQueResponde asc")
	public List<TestPreguntas> findDistinctTestPreguntasByAsignaturaIdOrderTest(@Param("asignaturaId") Long asignaturaId);
	
	@Query("FROM TestPreguntas tp WHERE tp.id IN (" +
		       "    SELECT MIN(subTp.id) " +
		       "    FROM TestPreguntas subTp " +
		       "    WHERE subTp.test.asignatura.id = :asignaturaId " +
		       "    GROUP BY subTp.puntuacionTest.id" +
		       ") order by tp.idAlumnoQueResponde asc, tp.test.id asc")
	public List<TestPreguntas> findDistinctTestPreguntasByAsignaturaIdOrderAlumno(@Param("asignaturaId") Long asignaturaId);


}
