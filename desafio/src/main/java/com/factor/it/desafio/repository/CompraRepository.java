package com.factor.it.desafio.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.factor.it.desafio.model.Compra;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Long> {
    @Query(value = """
        SELECT
            SUM(c.total)
        FROM compra c 
        WHERE c.cliente_id = :id 
        AND  DATE(c.fecha_compra) BETWEEN DATE(:fecha1) AND DATE(:fecha2);
    """, nativeQuery = true)
    float findByClienteIdyFechas(@Param("id") Long id, @Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);

}
