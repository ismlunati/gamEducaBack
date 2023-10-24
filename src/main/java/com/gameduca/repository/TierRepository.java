package com.gameduca.repository;

import org.springframework.data.repository.CrudRepository;

import com.gameduca.entity.Tier;

public interface TierRepository extends CrudRepository<Tier, Long> {
	
}
