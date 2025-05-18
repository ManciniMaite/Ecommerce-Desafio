package com.factor.it.desafio.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.factor.it.desafio.model.Carrito;
import com.factor.it.desafio.model.Compra;
import com.factor.it.desafio.model.DetalleCompra;
import com.factor.it.desafio.model.FechaEspecial;
import com.factor.it.desafio.model.ItemCarrito;
import com.factor.it.desafio.model.Producto;
import com.factor.it.desafio.model.Usuario;
import com.factor.it.desafio.model.DTO.CarritoAgregarProductoRq;
import com.factor.it.desafio.model.DTO.CarritoCrearRq;
import com.factor.it.desafio.model.DTO.CarritoEliminarProductoRq;
import com.factor.it.desafio.model.DTO.CarritoFinalizarRq;
import com.factor.it.desafio.model.descuentoStrategy.Comun;
import com.factor.it.desafio.model.descuentoStrategy.UsuarioVIP;
import com.factor.it.desafio.repository.CompraRepository;
import com.factor.it.desafio.repository.FechaEspecialRepository;

@Service
public class CarritoService {
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ProductoService productoService;

    @Autowired
    FechaEspecialRepository fechaEspecialRepository;

    @Autowired
    CompraRepository compraRepository;

    private Map<UUID, Carrito> carritos = new HashMap<>();

    public Carrito crearCarrito(CarritoCrearRq rq) {

        //un solo carrito activo por usuario
        Optional<Carrito> carritoExistente = carritos.values().stream()
            .filter(c -> c.getCliente().getNroDocumento().equals(rq.getUsuario()))
            .findFirst();

        if (carritoExistente.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El usuario ya tiene un carrito activo");
        }


        Carrito carrito = new Carrito();
        carrito.setId(UUID.randomUUID());

        carrito.setFecha(rq.getFecha());

        //buscamos el usuario
        Optional<Usuario> usuario = this.usuarioService.findByNroDocumento(rq.getUsuario());

        if(usuario.isPresent()){
            carrito.setCliente(usuario.get());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario no encontrado con documento: " + rq.getUsuario());
        }

        Optional<FechaEspecial> promocionPorFecha = this.fechaEspecialRepository.findByFecha(rq.getFecha());

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
                System.out.println("Carrito comun");
                Comun descuento = new Comun();
                carrito.setDescuento(descuento);
            }
        }

        if(rq.getAgregarProducto() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No es posible inicializar un carrito vacio.");
        }

        carritos.put(carrito.getId(), carrito);

        rq.getAgregarProducto().setIdCarrito(carrito.getId());
        carrito = this.agregarProducto(rq.getAgregarProducto());

        return carrito;
    }

    public Carrito agregarProducto(CarritoAgregarProductoRq rq) {
        Carrito carrito = carritos.get(rq.getIdCarrito());
        if (carrito == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }

        if (rq.getCantidad() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad no puede ser negativa");
        }


        Producto producto; 
        Optional<Producto> OptProducto = productoService.findById(rq.getIdProducto()); 

        if(OptProducto.isPresent()){
            producto = OptProducto.get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Producto no encontrado con id: " + rq.getIdProducto());
        }

        if(carrito.getItemsCarrito()==null || carrito.getItemsCarrito().isEmpty()){

            if (rq.getCantidad() == 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad no puede ser 0");
            }

            System.out.println("ITEMS null o vacio");
            carrito.setItemsCarrito(new ArrayList<>());
            ItemCarrito ic = new ItemCarrito();
            ic.setProducto(producto);
            ic.setCantidad(rq.getCantidad());
            ic.setTotal(ic.calcularTotal());
            carrito.getItemsCarrito().add(ic);
        } else{
            ///busco si el Producto ya esta en el carrito
            Optional<ItemCarrito> OptItem = carrito.getItemsCarrito().stream()
                .filter(i -> i.getProducto().getId().equals(rq.getIdProducto()))
                .findFirst();
            if (OptItem.isPresent()) {

                if (rq.getCantidad() == 0) { //si la cantidad es 0 lo eliminamos del carrito
                    CarritoEliminarProductoRq eliminarRq = new CarritoEliminarProductoRq();
                    eliminarRq.setIdCarrito(carrito.getId());
                    eliminarRq.setIdProducto(producto.getId());
                    return this.eliminarProducto(eliminarRq); 
                }

                //si esta actualizamos la cantidad
                OptItem.get().setCantidad(rq.getCantidad());
                OptItem.get().setTotal(OptItem.get().calcularTotal());
            } else {

                if (rq.getCantidad() == 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La cantidad no puede ser 0");
                }

                //si no esta agregamos un nuevo Item
                ItemCarrito ic = new ItemCarrito();
                ic.setProducto(producto);
                ic.setCantidad(rq.getCantidad());
                ic.setTotal(ic.calcularTotal());
                carrito.getItemsCarrito().add(ic);
            }
        }


        //precios del carrito
        carrito.setSubTotal(carrito.calcularTotal()); //total sin descuento
        carrito.setMontoDescuento(carrito.calcularDescuento()); //descuento
        carrito.setTotal(carrito.calcularTotalConDescuento()); //total con descuento

        return carrito;
    }

    public Carrito obtenerCarrito(UUID carritoId) {
        return carritos.get(carritoId);
    }

    public Compra finalizarCarrito(UUID idCarrito) {
        Carrito carrito = carritos.get(idCarrito);
        if (carrito == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }

        //Crear la compra para luego persistirla
        Compra compra = new Compra();
        compra.setCliente(carrito.getCliente());
        compra.setFechaCompra(carrito.getFecha());
        compra.setSubTotal(carrito.getSubTotal()); //monto sin descuento
        compra.setMontoDescuento(carrito.getMontoDescuento()); //descuento
        compra.setTotal(carrito.getTotal()); //subtotal - decuento

        //mapeo los items carrito a detalle de compra
        List<DetalleCompra> detalles = carrito.getItemsCarrito().stream().map(item -> {
            DetalleCompra d = new DetalleCompra();
            d.setProducto(item.getProducto());
            d.setCantidad(item.getCantidad());
            d.setPrecioUnitario(item.getProducto().getPrecio());
            d.setSubtotal(d.calcularSubtotal());
            return d;
        }).collect(Collectors.toList());

        compra.setDetallesCompra(detalles); //seteo detalles a compra
        detalles.forEach(d -> d.setCompra(compra)); //y compra a detalles

        // Se guarda la compra con sus detalles
        this.compraRepository.save(compra);

        // Elimina carrito de memoria
        carritos.remove(idCarrito);

        return compra;
    }

    public Carrito eliminarProducto (CarritoEliminarProductoRq rq){
        Carrito carrito = carritos.get(rq.getIdCarrito());
        if (carrito == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }

        boolean eliminado = carrito.getItemsCarrito().removeIf(
            item -> item.getProducto().getId().equals(rq.getIdProducto())
        );

        if (!eliminado) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado en el carrito");
        }

        //actualizamos el precio del carrito
        carrito.setSubTotal(carrito.calcularTotal());
        carrito.setMontoDescuento(carrito.calcularDescuento());
        carrito.setTotal(carrito.calcularTotalConDescuento());

        return carrito;
    }

    public void eliminarCarrito(UUID idCarrito) {
        Carrito carrito = carritos.remove(idCarrito);
        if (carrito == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }
    }

}
