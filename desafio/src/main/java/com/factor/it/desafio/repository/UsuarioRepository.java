package com.factor.it.desafio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.factor.it.desafio.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    Optional<Usuario> findByNroDocumento(String doc);

    @Query(value = """
        SELECT u.id, u.nombre, u.apellido, u.nro_documento
        FROM usuario u
        JOIN compra c ON u.id = c.cliente_id
        WHERE EXTRACT(YEAR FROM c.fecha_compra) = :anio
        AND EXTRACT(MONTH FROM c.fecha_compra) = :mes
        GROUP BY u.id, u.nombre, u.apellido
        HAVING SUM(c.total) >= 10000
        """, nativeQuery = true)
    List<Usuario> findUsuariosVIPEnMes(@Param("anio") int anio, @Param("mes") int mes);


    @Query(value = """
        SELECT u.id, u.nombre, u.apellido, u.nro_documento
        FROM usuario u
        JOIN compra c ON u.id = c.cliente_id
        WHERE EXTRACT(YEAR FROM c.fecha_compra) = :anio
        AND EXTRACT(MONTH FROM c.fecha_compra) = (:mes - 1)
        GROUP BY u.id, u.nombre, u.apellido
        HAVING SUM(c.total) >= 10000
        AND u.id NOT IN (
            SELECT cliente_id 
            FROM compra 
            WHERE EXTRACT(YEAR FROM fecha_compra) = :anio
            AND EXTRACT(MONTH FROM fecha_compra) = :mes
            GROUP BY cliente_id 
            HAVING SUM(total) >= 10000
        )
        """, nativeQuery = true)
    List<Usuario> findUsuariosNoVIP(@Param("anio") int anio, @Param("mes") int mes);
}
