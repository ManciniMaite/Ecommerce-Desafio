package com.factor.it.desafio.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factor.it.desafio.model.Producto;
import com.factor.it.desafio.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public Optional<Producto> findById(Long id){
        return this.productoRepository.findById(id);
    }

    public Iterable<Producto> obtenerTodos(){
        return this.productoRepository.findAll();
    }
}
