package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.Logro;

public interface LogroRepository extends CrudRepository<Logro, Long> {

	@Query("SELECT artefactoLogro.logro FROM ArtefactoLogro artefactoLogro where artefactoLogro.artefacto.asignatura.id =:idAsignatura")
	public List<Logro> findLogrosByAsignatura(@Param("idAsignatura") Long idAsignatura);
}
