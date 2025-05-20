import { ChangeDetectorRef, Component, inject, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { ItemCarrito } from '../model/ItemCarrito';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatFormField } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { AppComponent, ModalDialog } from '../app.component';
import { CarritoService } from '../services/carrito.service';
import { CarritoAgregarProductoRq } from '../model/CarritoAgregarProductoRq';
import { CarritoEliminarProductoRq } from '../model/CarritoEliminarProductoRq';
import { Carrito } from '../model/Carrito';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-card-itm-carrito',
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
  templateUrl: './card-itm-carrito.component.html',
  styleUrl: './card-itm-carrito.component.scss'
})
export class CardItmCarritoComponent implements OnChanges{
  serviceCarrito = inject(CarritoService); 

  @Input() item: ItemCarrito = new ItemCarrito();

  cantidad: number; 
  carrito:Carrito

  constructor(
    public app: AppComponent,
    private cdRef: ChangeDetectorRef,
    private router: Router,
    private dialog: MatDialog
  ) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['item'] && changes['item'].currentValue) {
      this.cantidad = this.item.cantidad;
      this.setCarrito();
    }
  }

  setCarrito(){
    this.serviceCarrito.carrito$.subscribe({
        next: (carrito) => {
          if (carrito) {
            this.carrito = carrito;
            this.cdRef.detectChanges();
          }
        }
      });
  }

  get imagenUrl(): string {
    const nombreArchivo = this.item.producto.nombre.trim().replace(/\s+/g, "-") + ".webp";
    return "assets/ImagenProductos/" + nombreArchivo;
  }

  decrementarCantidad(){
    if(this.cantidad>1){
      this.cantidad--;
      this.agregarAlCarrito();
    } else{
      this.cantidad=1 ;
    }
  }

  incrementarCantidad(){
    this.cantidad++;
    this.agregarAlCarrito();
  }

  agregarAlCarrito(){
    let rq: CarritoAgregarProductoRq = new CarritoAgregarProductoRq();
    rq.idCarrito = this.carrito.id;
    rq.idProducto=this.item.producto.id;
    rq.cantidad=this.cantidad;

    this.serviceCarrito.agregarProducto(rq).subscribe({
      next:(data)=>{
        this.serviceCarrito.actualizarCarrito(data);
        this.setCarrito();
        this.app.mostrarSnackbar("Carrito actializado");
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

  eliminar(){
    let rq: CarritoEliminarProductoRq = new CarritoEliminarProductoRq();
    rq.idCarrito=this.carrito.id;
    rq.idProducto=this.item.producto.id
    this.serviceCarrito.eliminarItemDelCarrito(rq).subscribe({
      next:(data)=>{
        if(data.itemsCarrito.length == 0){
          this.eliminarCarrito();
        }
        this.serviceCarrito.actualizarCarrito(data);
        this.setCarrito();
        this.app.mostrarSnackbar("Producto eliminado del carrito");
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

  eliminarCarrito(){
    this.serviceCarrito.eliminarCarrito(this.carrito.id).subscribe({
      next:(data)=>{
        this.serviceCarrito.actualizarCarrito(null);
        this.router.navigate(['']);
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
}
