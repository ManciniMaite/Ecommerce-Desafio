package com.factor.it.desafio.model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.factor.it.desafio.model.descuentoStrategy.Descuento;

import lombok.Data;

@Data
public class Carrito {
    private UUID id;
    private List<ItemCarrito> itemsCarrito;
    private Descuento descuento;
    private Double  subTotal; //total sin descuento
    private Double montoDescuento;
    private Double total; //total con descuento

    private LocalDateTime ultimaActualizacion;

    private Usuario cliente;

    private Date fecha;

    public Double calcularTotal(){
        Double total = 0.0;
        for(ItemCarrito item : this.itemsCarrito){
            total+=item.calcularTotal();
        }
        return total;
    }

    public Double calcularDescuento(){
        return this.descuento.calcularDescuento(this);
    }

    public Double calcularTotalConDescuento(){
        Double total = this.calcularTotal()-this.calcularDescuento();
        return total > 0 ? total : 0; //por si el descuento llega a ser mas grande que la compra
    }

    public void actualizar() {
        this.ultimaActualizacion = LocalDateTime.now();
    }
}
