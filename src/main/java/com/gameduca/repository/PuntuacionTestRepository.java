package com.gameduca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.ArtefactoLogro;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.PuntuacionTest;
import com.gameduca.entity.ReportePregunta;
import com.gameduca.entity.TestPreguntas;

import java.util.List;
import java.util.Optional;

@Repository
public interface PuntuacionTestRepository extends CrudRepository<PuntuacionTest, Long> {
	
	@Query("SELECT puntuacionTest FROM PuntuacionTest puntuacionTest WHERE puntuacionTest.testPreguntasList IN :testPregunta")
	public List<PuntuacionTest> findPuntuacionTestByOneTestPregunta(@Param("testPregunta") TestPreguntas testPregunta);

}
