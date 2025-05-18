package com.factor.it.desafio.service;

import java.sql.Date;
import java.time.LocalDate;
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
        LocalDate localDate = fecha.toLocalDate();
        LocalDate primerDiaMesAnterior = localDate.minusMonths(1).withDayOfMonth(1);

        Date mesAnterior = Date.valueOf(primerDiaMesAnterior);

        Optional<Usuario> us = this.findByNroDocumento(nroDoc);
        
        if(us.isPresent()){
            Double totalCompras = compraRepository.findByClienteIdyFechas(us.get().getId(), fecha, mesAnterior);
            if (totalCompras == null) {
                totalCompras = 0.0;
            }
            return totalCompras > 10000;
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con documento: " + nroDoc);
        }
    }

    public boolean esClienteVIP(String nroDoc, int mes, int anio){
        LocalDate primerDiaDelMes = LocalDate.of(anio, mes, 1);
        LocalDate ultimoDiaDelMes = LocalDate.of(anio, mes, 1).withDayOfMonth(LocalDate.of(anio, mes, 1).lengthOfMonth());

        Date fecha1 = Date.valueOf(primerDiaDelMes);
        Date fecha2 = Date.valueOf(ultimoDiaDelMes);

        Optional<Usuario> us = this.findByNroDocumento(nroDoc);
        
        if(us.isPresent()){
            Double totalCompras = compraRepository.findByClienteIdyFechas(us.get().getId(), fecha1, fecha2);
            if (totalCompras == null) {
                totalCompras = 0.0;
            }
            return totalCompras > 10000;
        } else {
            throw new IllegalArgumentException("Usuario no encontrado con documento: " + nroDoc);
        }
    }
}

