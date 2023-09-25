package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.Logro;

public interface LogroRepository extends CrudRepository<Logro, Long> {

	@Query("SELECT logro FROM Logro logro where logro.asignatura.id =:idAsignatura")
	public List<Logro> findLogrosByAsignatura(@Param("idAsignatura") Long idAsignatura);
}
