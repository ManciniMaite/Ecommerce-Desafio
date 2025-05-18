package com.factor.it.desafio.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class FechaEspecial {
    @Id
    @GeneratedValue(strategy = GenerationType .IDENTITY)
    private Long id;
    private Date fecha;
}
