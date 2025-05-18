package com.factor.it.desafio.model.DTO;

import java.sql.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class CarritoFinalizarRq {
    private UUID idCarrito;
    private Date fecha;
}
