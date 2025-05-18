package com.factor.it.desafio.model.descuentoStrategy;

import com.factor.it.desafio.model.Carrito;

public class FechaEspecial implements Descuento {
    @Override
    public Double calcularDescuento(Carrito carrito) {
        return 300.0;
    }
}
