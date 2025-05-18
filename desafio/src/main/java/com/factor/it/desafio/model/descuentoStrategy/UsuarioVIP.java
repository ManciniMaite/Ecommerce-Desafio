package com.factor.it.desafio.model.descuentoStrategy;

import java.util.Comparator;
import java.util.Optional;

import com.factor.it.desafio.model.Carrito;
import com.factor.it.desafio.model.ItemCarrito;

public class UsuarioVIP implements Descuento {
    @Override
    public Double calcularDescuento(Carrito carrito) {
        Double descuento = 500.0;

        //se bonifica el producto más barato - en caso de tener mas de un producto
        if(carrito.getItemsCarrito().size() > 1 ){
            // busco el producto mas barato dentro de los items del carrito
            Optional<ItemCarrito> masBarato = carrito.getItemsCarrito().stream()
                .min(Comparator.comparingDouble(item -> item.getProducto().getPrecio()));

            if (masBarato.isPresent()) {
                //se bonifica el precio del producto mas barato
                descuento += masBarato.get().getProducto().getPrecio();
            }
        }


        return descuento;
    }
}
