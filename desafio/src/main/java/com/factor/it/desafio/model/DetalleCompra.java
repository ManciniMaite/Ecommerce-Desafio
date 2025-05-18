package com.factor.it.desafio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private int cantidad;
    private Producto producto;
    private float precioUnitario; //Sirve para poder visualizar el historial de las compras y saber cual fue el precio del producto en el momento de realizar la compra

    @ManyToOne
    @JoinColumn(name = "compra_id")
    @JsonBackReference
    private Compra compra;

    public float calcularSubtotal(){
        return this.precioUnitario * this.cantidad;
    }
} 
