package com.gameduca.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gameduca.entity.Rol;
import com.gameduca.entity.RolNombre;

@Repository
public interface RolRepository extends CrudRepository<Rol, Long> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
