package com.factor.it.desafio.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.factor.it.desafio.model.FechaEspecial;

@Repository
public interface FechaEspecialRepository extends CrudRepository<FechaEspecial, Long>{
    Optional<FechaEspecial> findByFecha(Date fecha);
}
