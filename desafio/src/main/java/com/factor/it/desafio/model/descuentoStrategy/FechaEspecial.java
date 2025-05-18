package com.factor.it.desafio.model.descuentoStrategy;

import com.factor.it.desafio.model.Carrito;

public class FechaEspecial implements Descuento {
    @Override
    public float calcularDescuento(Carrito carrito) {
        return (float) 300;
    }
}
