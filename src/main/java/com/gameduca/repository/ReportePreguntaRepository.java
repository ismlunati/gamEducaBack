package com.gameduca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.ArtefactoLogro;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.ReportePregunta;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportePreguntaRepository extends CrudRepository<ReportePregunta, Long> {
	
	@Query("SELECT reportePregunta FROM ReportePregunta reportePregunta WHERE reportePregunta.alumno.usuario.nombreUsuario =:nombreUsuario")
	List<ReportePregunta> findReportePreguntaByAlumno(@Param("nombreUsuario") String nombreUsuario);
	
	@Query("SELECT reportePregunta FROM ReportePregunta reportePregunta WHERE reportePregunta.pregunta.asignatura.id =:asignaturaId")
	List<ReportePregunta> findReportePreguntaByAsignaturaId(@Param("asignaturaId") Long asignaturaId);


}