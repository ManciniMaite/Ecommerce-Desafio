package com.factor.it.desafio.model.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class CarritoAgregarProductoRq {
    private UUID idCarrito;
    private Long idProducto;
    private int cantidad;
}
