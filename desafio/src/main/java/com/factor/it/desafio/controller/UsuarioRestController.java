package com.factor.it.desafio.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.factor.it.desafio.model.Usuario;
import com.factor.it.desafio.service.UsuarioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("Clientes")
public class UsuarioRestController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/usuarios-VIP/{mes}/{anio}")
    public List<Usuario> getClientesVIP(@PathVariable("mes") int mes, @PathVariable("anio") int anio) {
        return this.usuarioService.obtenerUsuariosVIPEnMes(mes, anio);
    }

    @GetMapping("/usuarios-No-VIP/{mes}/{anio}")
    public List<Usuario> getClientesNoVIP(@PathVariable("mes") int mes, @PathVariable("anio") int anio) {
        return this.usuarioService.obtenerUsuariosNoVIPEnMes(mes, anio);
    }
    
    
}
