import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductoService } from '../services/producto.service';
import { Producto } from '../model/Producto';
import { MatCardModule } from '@angular/material/card';
import { CommonModule, Location } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { AppComponent, ModalDialog } from '../app.component';
import { CarritoService } from '../services/carrito.service';
import { CarritoCrearRq } from '../model/CarritoCrearRq';
import { CarritoAgregarProductoRq } from '../model/CarritoAgregarProductoRq';
import { Carrito } from '../model/Carrito';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-ver-producto',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatFormField,
    MatInputModule,
  ],
  templateUrl: './ver-producto.component.html',
  styleUrl: './ver-producto.component.scss'
})
export class VerProductoComponent implements OnInit {
  producto: Producto
  cantidad: number = 1;
  carrito:Carrito;
  service = inject(ProductoService); 
  serviceCarrito = inject(CarritoService); 
  dialog= inject(MatDialog);

  constructor(
    private route: ActivatedRoute,
    private location: Location,
    public app: AppComponent,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.serviceCarrito.carrito$.subscribe({
        next: (carrito) => {
          if (carrito) {
            this.carrito = carrito;
          }
        }
      });
    let  idProducto = +this.route.snapshot.paramMap.get('idProducto');
    if(idProducto){
      this.service.findById(idProducto).subscribe({
        next: (data)=>{
          this.producto = data;
          console.log(data);
        }
      });
    } else{
      const mensajeError = 'No se pudo recuperar el id del Producto';
      this.dialog.open(ModalDialog, {
        disableClose: true,
        data: {
          tipo: 'error',
          titulo: 'Error',
          mensaje: mensajeError,
          cerrar: 'Cerrar'
        }
      });
    }
  }

  get imagenUrl(): string {
    const nombreArchivo = this.producto.nombre.trim().replace(/\s+/g, "-") + ".webp";
    return "assets/ImagenProductos/" + nombreArchivo;
  }

  decrementarCantidad(){
    this.cantidad>1? this.cantidad-- : this.cantidad=1 ;
  }

  incrementarCantidad(){
    this.cantidad++;
  }

  agregarAlCarrito(){
    if(this.carrito){
      //si hay carrito actualizar

      let rq: CarritoAgregarProductoRq = new CarritoAgregarProductoRq();
      rq.idCarrito = this.carrito.id;
      rq.idProducto=this.producto.id;
      rq.cantidad=this.cantidad;

      this.serviceCarrito.agregarProducto(rq).subscribe({
        next:(data)=>{
          this.serviceCarrito.actualizarCarrito(data);
          this.app.mostrarSnackbar("Producto agregado al carrito");
        }, error: (error)=>{
          console.log(error);
          const mensajeError = error.error?.message || 'Ocurrió un error inesperado';
          this.dialog.open(ModalDialog, {
            disableClose: true,
            data: {
              tipo: 'error',
              titulo: 'Error',
              mensaje: mensajeError,
              cerrar: 'Cerrar'
            }
          });
        }
      });

    } else{
      // sino crear carrito
      if(this.app.filtroUsuario && this.app.filtroFecha){
        let rq: CarritoCrearRq = new CarritoCrearRq();
        rq.fecha=this.app.filtroFecha;
        rq.usuario=this.app.filtroUsuario;
        rq.agregarProducto = new CarritoAgregarProductoRq();
        rq.agregarProducto.cantidad = this.cantidad;
        rq.agregarProducto.idProducto = this.producto.id;

        this.serviceCarrito.crearCarrito(rq).subscribe({
          next:(data) =>{
            this.serviceCarrito.actualizarCarrito(data);
            this.carrito = data;
          }, error:(error)=>{
            console.log(error);
            const mensajeError = error.error?.message || 'Ocurrió un error inesperado';
            this.dialog.open(ModalDialog, {
              disableClose: true,
              data: {
                tipo: 'error',
                titulo: 'Error',
                mensaje: mensajeError,
                cerrar: 'Cerrar'
              }
            });
          }
        });

      }else{
        console.log('no hay us ni fecha')
        const mensajeError = 'Por favor completá los datos de usuario y fecha';
          this.dialog.open(ModalDialog, {
            disableClose: true,
            data: {
              tipo: 'error',
              titulo: 'Error',
              mensaje: mensajeError,
              cerrar: 'Cerrar'
            }
          });
      }
    }

  }

  comprar(){
    console.log('comprar')
    if(this.carrito){
      let existe: boolean = this.carrito.itemsCarrito.some(item => item.producto.id == this.producto.id);
      console.log(existe)
      if(existe){
        this.router.navigate(['ver-carrito/'+this.carrito.id]);
      } else{
        let rq: CarritoAgregarProductoRq = new CarritoAgregarProductoRq();
        rq.idCarrito = this.carrito.id;
        rq.idProducto=this.producto.id;
        rq.cantidad=this.cantidad;

        this.serviceCarrito.agregarProducto(rq).subscribe({
          next:(data)=>{
            this.serviceCarrito.actualizarCarrito(data);
            this.carrito = data;
            this.router.navigate(['ver-carrito/'+this.carrito.id]);
          }, error: (error)=>{
            console.log(error);
            const mensajeError = error.error?.message || 'Ocurrió un error inesperado';
            this.dialog.open(ModalDialog, {
              disableClose: true,
              data: {
                tipo: 'error',
                titulo: 'Error',
                mensaje: mensajeError,
                cerrar: 'Cerrar'
              }
            });
          }
        });
      }
    } else{
      console.log('crear carrito')
      if(this.app.filtroUsuario && this.app.filtroFecha){
        let rq: CarritoCrearRq = new CarritoCrearRq();
        rq.fecha=this.app.filtroFecha;
        rq.usuario=this.app.filtroUsuario;
        rq.agregarProducto = new CarritoAgregarProductoRq();
        rq.agregarProducto.cantidad = this.cantidad;
        rq.agregarProducto.idProducto = this.producto.id;

        this.serviceCarrito.crearCarrito(rq).subscribe({
          next:(data) =>{
            this.serviceCarrito.actualizarCarrito(data);
            this.carrito = data
            this.router.navigate(['ver-carrito/'+data.id]);
          }, error:(error)=>{
            console.log(error);
            const mensajeError = error.error?.message || 'Ocurrió un error inesperado';
            this.dialog.open(ModalDialog, {
              disableClose: true,
              data: {
                tipo: 'error',
                titulo: 'Error',
                mensaje: mensajeError,
                cerrar: 'Cerrar'
              }
            });
          }
        });

      }else{
        console.log('no hay us ni fecha')
        const mensajeError = 'Por favor completá los datos de usuario y fecha';
        this.dialog.open(ModalDialog, {
          disableClose: true,
          data: {
            tipo: 'error',
            titulo: 'Error',
            mensaje: mensajeError,
            cerrar: 'Cerrar'
          }
        });
      }
    }
  }

  onVolver(){
    this.location.back();
  }
}
