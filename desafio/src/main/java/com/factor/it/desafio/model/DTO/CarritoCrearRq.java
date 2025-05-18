package com.factor.it.desafio.model.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public class CarritoCrearRq {
    private String usuario;
    private Date fecha;
}
