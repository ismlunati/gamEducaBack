package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.Test;

public interface TestRepository extends CrudRepository<Test, Long>{
	
	@Query("SELECT t FROM Test t WHERE t.asignatura.id = :asignaturaId AND t.visible = true")
	public List<Test> findTestVisiblesByAsignaturaId(@Param("asignaturaId") Long asignaturaId);

}
