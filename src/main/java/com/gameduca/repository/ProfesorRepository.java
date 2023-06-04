package com.gameduca.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Asignatura;
import com.gameduca.entity.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Long> {
	
	@Query("SELECT profesor FROM Profesor profesor WHERE profesor.usuario.nombreUsuario =:nombre")
	public Profesor findProfesorByNombre(@Param("nombre") String nombre);
	

}
