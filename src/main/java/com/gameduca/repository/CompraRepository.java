package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.AlumnoReto;
import com.gameduca.entity.Artefacto;
import com.gameduca.entity.Compra;
import com.gameduca.entity.Reto;

public interface CompraRepository extends CrudRepository<Compra, Long> {
	
	@Query("SELECT compra FROM Compra compra where compra.alumno.usuario.nombreUsuario =:nombreUsuario and compra.artefacto.asignatura.id =:idAsignatura")
	public List<Compra> findComprasByAlumnoyAsignaturas(@Param("nombreUsuario") String nombreUsuario, @Param("idAsignatura") Long idAsignatura);

	@Query("SELECT compra.artefacto FROM Compra compra where compra.alumno.usuario.nombreUsuario =:nombreUsuario and compra.artefacto.asignatura.id =:idAsignatura")
	public List<Artefacto> findArtefactosByAlumnoyAsignaturas(@Param("nombreUsuario") String nombreUsuario, @Param("idAsignatura") Long idAsignatura);
}
