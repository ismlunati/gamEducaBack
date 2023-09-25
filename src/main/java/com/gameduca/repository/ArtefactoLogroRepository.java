package com.gameduca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.ArtefactoLogro;
import com.gameduca.entity.Asignatura;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtefactoLogroRepository extends CrudRepository<ArtefactoLogro, Long> {
	
	@Query("SELECT artefactoLogro FROM ArtefactoLogro artefactoLogro WHERE artefactoLogro.artefacto.id =:artefactoId")
	List<ArtefactoLogro> findArtefactoLogroByArtefactoId(@Param("artefactoId") Long artefactoId);


}
