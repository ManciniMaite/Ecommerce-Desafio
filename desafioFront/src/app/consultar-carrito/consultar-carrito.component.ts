import { Component, Inject, inject, OnInit } from '@angular/core';
import { CarritoService } from '../services/carrito.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CommonModule, Location } from '@angular/common';
import { AppComponent, ModalDialog } from '../app.component';
import { Carrito } from '../model/Carrito';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import { MatFormField } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { CardItmCarritoComponent } from '../card-itm-carrito/card-itm-carrito.component';
import {MatDividerModule} from '@angular/material/divider';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-consultar-carrito',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    MatIconModule,
    MatButtonModule,
    FormsModule,
    MatFormField,
    MatInputModule,
    CardItmCarritoComponent,
    MatDividerModule
  ],
  templateUrl: './consultar-carrito.component.html',
  styleUrl: './consultar-carrito.component.scss'
})
export class ConsultarCarritoComponent implements OnInit{
  serviceCarrito = inject(CarritoService); 
  dialog = inject(MatDialog);

  carrito: Carrito;

  constructor(
    private location: Location,
    public app: AppComponent,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.serviceCarrito.carrito$.subscribe((carrito) => {
      if (carrito) {
        this.carrito = carrito;
      }
    });
  }
  onVolver(){
    this.location.back()
  }

  onDialogFinalizarCompra(){
    let dialog1 = this.dialog.open(ModalDialog, {
      disableClose: true,
      data: {
        tipo: 'info',
        mensaje: "¿Está seguro que desea finalizar la compra?",
        textoAceptar:'Finalizar',
        cerrar: 'Cerrar',
        onAceptClick: ()=>{
          this.finalizarCompra();
        },
        onNoClick: ()=>{
          dialog1.close();
          let dialog2 = this.dialog.open(ModalDialog, {
            disableClose: true,
            data: {
              tipo: 'info',
              mensaje: "¿Queres seguir comprando?",
              cerrar: 'Si',
              textoAceptar:'No',
              onAceptClick: ()=>{
                this.eliminarCarrito();
              }
            }
          });
        }
      }
    });
  }

  finalizarCompra(){
    this.serviceCarrito.finalizarCarrito(this.carrito.id).subscribe({
      next:(data)=>{
        this.serviceCarrito.actualizarCarrito(null);
        this.dialog.open(ModalDialog, {
          disableClose: true,
          data: {
            tipo: 'normal',
            mensaje: "Compra realizada con éxito!",
            textoAceptar:'Cerrar',
            onAceptClick: ()=>{
              this.router.navigate(['']);
            }
          }
        });
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
