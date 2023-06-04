package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.AlumnoAsignatura;
import com.gameduca.entity.Asignatura;

@Repository
public interface AlumnoAsignaturaRepository extends CrudRepository<AlumnoAsignatura, Long> {
	
	@Query("SELECT alumnoasignatura.asignatura FROM AlumnoAsignatura alumnoasignatura where alumnoasignatura.alumno.usuario.nombreUsuario =:nombreUsuario")
	public List<Asignatura> findAsignaturasByAlumno(@Param("nombreUsuario") String nombreUsuario);
	
	@Query("SELECT alumnoasignatura.alumno FROM AlumnoAsignatura alumnoasignatura where alumnoasignatura.asignatura.id =:id and alumnoasignatura.estado = 'Pendiente'")
	public List<Alumno> findAlumnosPendientesEnAsignatura(@Param("id") Long id);
	
	@Query("SELECT alumnoasignatura FROM AlumnoAsignatura alumnoasignatura where alumnoasignatura.asignatura.id =:idAsignatura and alumnoasignatura.alumno.id =:idAlumno")
	public AlumnoAsignatura findAlumnoAsignaturaByIdAlumnoIdAsignatura(@Param("idAsignatura") Long idAsignatura, @Param("idAlumno") Long idAlumno);

}

