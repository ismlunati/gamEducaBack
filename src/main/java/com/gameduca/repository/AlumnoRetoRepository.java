package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.AlumnoReto;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Reto;

public interface AlumnoRetoRepository extends CrudRepository<AlumnoReto, Long> {
	
	@Query("SELECT alumnoReto.reto FROM AlumnoReto alumnoReto where alumnoReto.alumno.usuario.nombreUsuario =:nombreUsuario and alumnoReto.estado = 'COMPLETADO' and alumnoReto.reto.asignatura.id =:idAsignatura")
	public List<Reto> findRetosByAlumnoyAsignaturas(@Param("nombreUsuario") String nombreUsuario, @Param("idAsignatura") Long idAsignatura);
	
	@Query("SELECT alumnoReto FROM AlumnoReto alumnoReto where alumnoReto.alumno.usuario.nombreUsuario =:nombreUsuario and alumnoReto.reto.id =:idReto")
	public AlumnoReto findAlumnoRetoByRetoyAlumno(@Param("nombreUsuario") String nombreUsuario, @Param("idReto") Long idReto);
	
	@Query("SELECT alumnoReto FROM AlumnoReto alumnoReto where alumnoReto.reto.asignatura.id =:idAsignatura")
	public List<AlumnoReto> findAlumnoRetoByAsignatura(@Param("idAsignatura") Long idAsignatura);

}