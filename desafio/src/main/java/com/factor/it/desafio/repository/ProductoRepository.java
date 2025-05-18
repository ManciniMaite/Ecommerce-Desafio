package com.factor.it.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.factor.it.desafio.model.Producto;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {
}