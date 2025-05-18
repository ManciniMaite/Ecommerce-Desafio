package com.factor.it.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.factor.it.desafio.model.DetalleCompra;

@Repository
public interface DetalleCompraRepository extends CrudRepository<DetalleCompra, Long>{

}
