import { Component, inject,  Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { Producto } from '../model/Producto';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-card-producto',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
  ],
  templateUrl: './card-producto.component.html',
  styleUrl: './card-producto.component.scss'
})
export class CardProductoComponent {
  @Input() producto: Producto;

  router = inject(Router);

  get imagenUrl(): string {
    const nombreArchivo = this.producto.nombre.trim().replace(/\s+/g, "-") + ".webp";
    return "assets/ImagenProductos/" + nombreArchivo;
  }

  verProducto(){
    this.router.navigate(['ver-producto/'+this.producto.id]);
  }
}