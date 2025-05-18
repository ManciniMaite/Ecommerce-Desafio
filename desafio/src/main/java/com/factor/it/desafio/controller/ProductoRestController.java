package com.factor.it.desafio.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.factor.it.desafio.model.Producto;
import com.factor.it.desafio.service.ProductoService;

@RestController
@RequestMapping("Producto")
public class ProductoRestController {
    @Autowired
    ProductoService productoService;

    @GetMapping
    public ResponseEntity<Iterable<Producto>> obtenerTodos() {
        Iterable<Producto> productos = productoService.obtenerTodos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(@PathVariable("id") Long id) {
       return productoService.findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
