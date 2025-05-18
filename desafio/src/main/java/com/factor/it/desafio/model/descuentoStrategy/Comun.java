package com.factor.it.desafio.model.descuentoStrategy;

import com.factor.it.desafio.model.Carrito;
import com.factor.it.desafio.model.ItemCarrito;

public class Comun implements Descuento {
    @Override
    public float calcularDescuento(Carrito carrito) {
        float descuento;
        int cantidadProductos = carrito.getItemsCarrito().stream().mapToInt(ItemCarrito::getCantidad).sum();

        //Si se compran exactamente 4 productos: Se hace un descuento general del 25%.
        descuento = cantidadProductos >= 4 ? (float) (carrito.calcularTotal()*0.25) : (float) 0; 

        //Si se compran más de 10 productos: A ese carrito se le realizara ADEMAS un descuento de $100.
        descuento = cantidadProductos > 10 ? descuento+100 : descuento; 

        return descuento;
    }
}
