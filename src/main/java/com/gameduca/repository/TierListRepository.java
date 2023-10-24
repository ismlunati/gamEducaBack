package com.gameduca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gameduca.entity.TierList;

public interface TierListRepository extends CrudRepository<TierList, Long> {
	
	@Query("SELECT tierList FROM TierList tierList WHERE tierList.asignatura.id =:asignaturaId")
	List<TierList> findTierListByAsignatura(@Param("asignaturaId") Long asignaturaId);
	
}
