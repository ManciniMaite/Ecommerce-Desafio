import { Component, EventEmitter, inject, Output } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatFormField, MatInputModule} from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule, provideNativeDateAdapter} from '@angular/material/core';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatIconButton } from '@angular/material/button';
import { AppComponent } from '../app.component';
import { Router } from '@angular/router';
import { CarritoService } from '../services/carrito.service';
import { Carrito } from '../model/Carrito';
import {MatTooltipModule} from '@angular/material/tooltip';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatFormField,
    MatIconModule,
    MatDatepickerModule,
    MatToolbarModule,
    MatIconButton,
    MatNativeDateModule,
    MatTooltipModule
  ],
  providers: [provideNativeDateAdapter()],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  serviceCarrito = inject(CarritoService);
  carrito: Carrito;

  @Output() usuarioChange = new EventEmitter<string>();
  @Output() fechaChange = new EventEmitter<string>();

  constructor(
    public app: AppComponent,
    private router: Router
  ) {
    this.serviceCarrito.carrito$.subscribe({
        next: (carrito) => {
            this.carrito = carrito;
        }
      });
  }

  usuario = '';
  fecha = '';

  onUsuarioChange() {
    this.usuarioChange.emit(this.usuario);
  }

  onFechaChange() {
    this.fechaChange.emit(this.fecha);
  }

  clickCarrito(){
    console.log(this.carrito);
    if(this.carrito){
      this.router.navigate(['ver-carrito/'+this.carrito.id]);
    } else{
      this.app.mostrarSnackbar("Carrito vacio");
    }
  }

  onIrInicio(){
    this.router.navigate(['']);
  }

  consultarClientes(){
    this.router.navigate(['/consultar-clientes']);
  }
}
