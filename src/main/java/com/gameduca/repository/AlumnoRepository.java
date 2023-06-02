package com.gameduca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Asignatura;

import java.util.Optional;

@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
	
	@Query("SELECT alumno FROM Alumno alumno WHERE alumno.usuario.nombreUsuario =:nombreUsuario")
	Alumno findAlumnoByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);

}
