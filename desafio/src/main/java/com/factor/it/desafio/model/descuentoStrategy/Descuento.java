package com.factor.it.desafio.model.descuentoStrategy;

import com.factor.it.desafio.model.Carrito;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@type")
public interface Descuento {
    public Double calcularDescuento(Carrito carrito);
}
