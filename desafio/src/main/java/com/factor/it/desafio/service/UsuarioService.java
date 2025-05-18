package com.factor.it.desafio.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.factor.it.desafio.model.Usuario;
import com.factor.it.desafio.repository.CompraRepository;
import com.factor.it.desafio.repository.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    CompraRepository compraRepository;

    public Optional<Usuario> findByNroDocumento(String nroDoc){
        return usuarioRepository.findByNroDocumento(nroDoc);
    }

    public boolean esClienteVIP(String nroDoc, Date fecha){
        //ES CLIENTE VIP SI LAS COMPRAS DEL MES SUPERAN LOS 10000
        LocalDate localDate = fecha.toLocalDate();
        LocalDate primerDiaMes = localDate.withDayOfMonth(1);

        Date mesActual = Date.valueOf(primerDiaMes);

        Optional<Usuario> us = this.findByNroDocumento(nroDoc);
        
        if(us.isPresent()){
            Double totalCompras = compraRepository.findByClienteIdyFechas(us.get().getId(), fecha, mesActual);
            if (totalCompras == null) {
                totalCompras = 0.0;
            }
            return totalCompras > 10000;
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con documento: " + nroDoc);
        }
    }

    public List<Usuario> obtenerUsuariosVIPEnMes(int mes, int anio) {
        return usuarioRepository.findUsuariosVIPEnMes(anio, mes);
    }

    public List<Usuario> obtenerUsuariosNoVIPEnMes(int mes, int anio) {
        return usuarioRepository.findUsuariosNoVIP(anio, mes);
    }

}

