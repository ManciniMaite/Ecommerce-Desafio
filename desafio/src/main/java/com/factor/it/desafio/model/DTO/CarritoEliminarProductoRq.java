package com.factor.it.desafio.model.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class CarritoEliminarProductoRq {
    private UUID idCarrito;
    private Long idProducto;
}
