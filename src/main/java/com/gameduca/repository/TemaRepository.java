package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.Tema;

public interface TemaRepository extends CrudRepository<Tema, Long>{
	
	@Query("SELECT tema FROM Tema tema where tema.asignatura.id =:asignaturaId")
	public List<Tema> obtenerTemasDeUnaAsignatura(@Param("asignaturaId") Long asignaturaId);

}
