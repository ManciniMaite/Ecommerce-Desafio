package com.factor.it.desafio.model;

import java.util.List;
import java.util.UUID;

import com.factor.it.desafio.model.descuentoStrategy.Descuento;

import lombok.Data;

@Data
public class Carrito {
    private UUID id;

    private List<ItemCarrito> itemsCarrito;
    private Descuento descuento;

    public float calcularTotal(){
        float total = 0;
        for(ItemCarrito item : this.itemsCarrito){
            total+=item.calcularTotal();
        }
        return total;
    }

    public float calcularDescuento(){
        return this.descuento.calcularDescuento(this);
    }

    public float calcularTotalConDescuento(){
        return this.calcularTotal()-this.calcularDescuento();
    }
}
