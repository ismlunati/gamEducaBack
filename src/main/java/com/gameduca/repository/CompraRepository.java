package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.AlumnoReto;
import com.gameduca.entity.Compra;
import com.gameduca.entity.Reto;

public interface CompraRepository extends CrudRepository<Compra, Long> {
	
//	@Query("SELECT alumnoreto.reto FROM AlumnoReto alumnoReto where alumnoreto.alumno.usuario.nombreUsuario =:nombreUsuario and alumnoreto.estado = 'Completado' and alumnoreto.reto.asignatura.id =:idAsignatura")
//	public List<Reto> findRetosByAlumnoyAsignaturas(@Param("nombreUsuario") String nombreUsuario, @Param("idAsignatura") Long idAsignatura);

}
