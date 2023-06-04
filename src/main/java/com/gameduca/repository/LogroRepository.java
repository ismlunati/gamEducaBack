package com.gameduca.repository;

import org.springframework.data.repository.CrudRepository;

import com.gameduca.entity.Alumno;
import com.gameduca.entity.Logro;

public interface LogroRepository extends CrudRepository<Logro, Long> {

}
