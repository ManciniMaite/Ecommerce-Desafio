package com.factor.it.desafio.model.descuentoStrategy;

import com.factor.it.desafio.model.Carrito;

public interface Descuento {
    public float calcularDescuento(Carrito carrito);
}
