package com.factor.it.desafio.model;

import lombok.Data;

@Data
public class ItemCarrito {
    private int cantidad;
    private Producto producto; 
    private Double total;

    public Double calcularTotal(){
        return this.producto.getPrecio() * this.cantidad;
    }
}
