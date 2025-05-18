package com.factor.it.desafio.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.factor.it.desafio.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
    Optional<Usuario> findByNroDocumento(String doc);
}
