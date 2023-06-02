package com.gameduca.repository;

import com.gameduca.entity.Asignatura;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AsignaturaRepository extends CrudRepository<Asignatura, Long>{
	
	@Query("SELECT asignatura.codigo FROM Asignatura asignatura")
	public List<String> findAllCodigoAsignatura();
	
	@Query("SELECT asignatura FROM Asignatura asignatura WHERE asignatura.codigo =:codigo")
	public Asignatura findAsignaturaByCodigo(@Param("codigo") String codigo);
	
	@Query("SELECT asignatura FROM Asignatura asignatura WHERE asignatura.profesor.usuario.nombreUsuario =:nombreUsuario")
	public List<Asignatura> findAsignaturasByProfesor(@Param("nombreUsuario") String nombreUsuario);



}
