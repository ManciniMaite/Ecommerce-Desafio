package com.factor.it.desafio.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factor.it.desafio.model.Carrito;
import com.factor.it.desafio.model.FechaEspecial;
import com.factor.it.desafio.model.DTO.CarritoCrearRq;
import com.factor.it.desafio.model.descuentoStrategy.Comun;
import com.factor.it.desafio.model.descuentoStrategy.UsuarioVIP;
import com.factor.it.desafio.repository.FechaEspecialRepository;

@Service
public class CarritoService {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    FechaEspecialRepository fechaEspecialRepository;

    private Map<UUID, Carrito> carritos = new HashMap<>();

    public Carrito crearCarrito(CarritoCrearRq rq) {

        Carrito carrito = new Carrito();
        carrito.setId(UUID.randomUUID());

        Optional<FechaEspecial> promocionPorFecha = Optional.empty();
        try{
            promocionPorFecha = this.fechaEspecialRepository.findByFecha(rq.getFecha());
        } catch(Exception e){

        }

        //se prioriza la promocion por fecha
        if(promocionPorFecha.isPresent()){
            com.factor.it.desafio.model.descuentoStrategy.FechaEspecial descuento = new com.factor.it.desafio.model.descuentoStrategy.FechaEspecial();
            carrito.setDescuento(descuento);
        } else{
            boolean esVIP = this.usuarioService.esClienteVIP(rq.getUsuario(),rq.getFecha());
            if(esVIP){
                UsuarioVIP descuento = new UsuarioVIP();
                carrito.setDescuento(descuento);
            } else{
                Comun descuento = new Comun();
                carrito.setDescuento(descuento);
            }
        }

        carritos.put(carrito.getId(), carrito);
        return carrito;
    }

}
