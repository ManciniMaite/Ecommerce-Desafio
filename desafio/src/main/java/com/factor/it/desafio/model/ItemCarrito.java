package com.factor.it.desafio.model;

import java.util.UUID;

import lombok.Data;

@Data
public class ItemCarrito {
    private int cantidad;
    private Producto producto; 

    public float calcularTotal(){
        return this.producto.getPrecio() * this.cantidad;
    }
}
