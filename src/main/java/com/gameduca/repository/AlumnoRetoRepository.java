package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.AlumnoReto;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Reto;

public interface AlumnoRetoRepository extends CrudRepository<AlumnoReto, Long> {
	
	@Query("SELECT alumnoReto.reto FROM AlumnoReto alumnoReto where alumnoReto.alumno.usuario.nombreUsuario =:nombreUsuario and alumnoReto.estado = 'Completado' and alumnoReto.reto.asignatura.id =:idAsignatura")
	public List<Reto> findRetosByAlumnoyAsignaturas(@Param("nombreUsuario") String nombreUsuario, @Param("idAsignatura") Long idAsignatura);

}