package com.factor.it.desafio.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaCompra;
    private float subTotal; //total sin descuento
    private float montoDescuento;
    private float total; //total con descuento
    private float descuento; 
    
    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<DetalleCompra> detallesCompra;

    @ManyToOne
    @JoinColumn (name = "cliente_id")
    private Usuario cliente;
}
