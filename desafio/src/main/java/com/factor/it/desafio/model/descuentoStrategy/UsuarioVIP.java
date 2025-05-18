package com.factor.it.desafio.model.descuentoStrategy;

import java.util.Comparator;
import java.util.Optional;

import com.factor.it.desafio.model.Carrito;
import com.factor.it.desafio.model.ItemCarrito;

public class UsuarioVIP implements Descuento {
    @Override
    public float calcularDescuento(Carrito carrito) {
        float descuento = 500;

        //se bonifica el producto más barato
        Optional<ItemCarrito> masBarato = carrito.getItemsCarrito().stream()
            .min(Comparator.comparingDouble(item -> item.calcularTotal()));

        if (masBarato.isPresent()) {
            //se bonifica el total de ese producto
            descuento += masBarato.get().calcularTotal();
        }


        return descuento;
    }
}
