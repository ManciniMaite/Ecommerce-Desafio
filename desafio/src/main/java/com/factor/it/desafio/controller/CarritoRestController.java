package com.factor.it.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.factor.it.desafio.model.Carrito;
import com.factor.it.desafio.model.DTO.CarritoCrearRq;
import com.factor.it.desafio.service.CarritoService;

@RestController
@RequestMapping("Carrito")
public class CarritoRestController {
    @Autowired
    CarritoService carritoService;

    @PostMapping("/crear")
    public ResponseEntity<Carrito> crear(@RequestBody CarritoCrearRq rq) {
        return ResponseEntity.ok(carritoService.crearCarrito(rq));
    }
}
