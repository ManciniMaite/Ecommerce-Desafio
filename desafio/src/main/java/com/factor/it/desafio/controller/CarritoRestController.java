package com.factor.it.desafio.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factor.it.desafio.model.Carrito;
import com.factor.it.desafio.model.Compra;
import com.factor.it.desafio.model.DTO.CarritoAgregarProductoRq;
import com.factor.it.desafio.model.DTO.CarritoCrearRq;
import com.factor.it.desafio.model.DTO.CarritoEliminarProductoRq;
import com.factor.it.desafio.model.DTO.CarritoFinalizarRq;
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

    @PutMapping("/agregar")
    public ResponseEntity<Carrito> agregarProducto(@RequestBody CarritoAgregarProductoRq rq ) {
        return ResponseEntity.ok(carritoService.agregarProducto(rq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> verCarrito(@PathVariable UUID id) {
        return ResponseEntity.ok(carritoService.obtenerCarrito(id));
    }

    @PostMapping("/finalizar")
    public Compra finalizarCarrito(@RequestBody CarritoFinalizarRq rq) {
        return this.carritoService.finalizarCarrito(rq);
    }

    @DeleteMapping("/eliminar-producto")
    public ResponseEntity<Carrito> eliminarItemDelCarrito(@RequestBody CarritoEliminarProductoRq rq) {
        return ResponseEntity.ok(carritoService.eliminarProducto(rq));
    }

    @DeleteMapping("/eliminar-carrito/{idCarrito}")
    public ResponseEntity<?> eliminarCarrito(@PathVariable UUID idCarrito) {
        try {
            carritoService.eliminarCarrito(idCarrito);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    
}
