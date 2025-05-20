import { Component, inject, OnInit } from '@angular/core';
import { ProductoService } from '../services/producto.service';
import { Producto } from '../model/Producto';
import { CardProductoComponent } from '../card-producto/card-producto.component';
import { CommonModule } from '@angular/common';
import { ModalDialog } from '../app.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-listado-productos',
  standalone: true,
  imports: [
    CommonModule,
    CardProductoComponent,
  ],
  templateUrl: './listado-productos.component.html',
  styleUrl: './listado-productos.component.scss'
})
export class ListadoProductosComponent implements OnInit {
  productos: Producto[]

  service = inject(ProductoService);
  dialog = inject(MatDialog); 

  ngOnInit(): void {
      this.service.obtenerTodos().subscribe({
        next: (data)=>{
          this.productos = data;
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
