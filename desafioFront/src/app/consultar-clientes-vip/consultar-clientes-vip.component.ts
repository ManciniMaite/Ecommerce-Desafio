import { Component, inject, signal } from '@angular/core';
import { Usuario } from '../model/Usuario';
import { UsuarioService } from '../services/usuario.service';
import { MatDialog } from '@angular/material/dialog';
import { ModalDialog } from '../app.component';
import { CommonModule, Location } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatSelectModule } from '@angular/material/select';
import { MatOptionModule } from '@angular/material/core';
import { Router } from '@angular/router';

interface Mes {
  numero: number;
  nombre: string;
}

@Component({
  selector: 'app-consultar-clientes-vip',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatExpansionModule,
    MatOptionModule
  ],
  templateUrl: './consultar-clientes-vip.component.html',
  styleUrl: './consultar-clientes-vip.component.scss'
})
export class ConsultarClientesVipComponent {
  usuarioService = inject(UsuarioService);
  dialog = inject(MatDialog);
  router = inject(Router);

  readonly panelOpenState = signal(false);

  anios: number[] = Array.from({ length: 31 }, (_, i) => 1995 + i);

  meses: Mes[] = [
    { numero: 1, nombre: 'Enero' },
    { numero: 2, nombre: 'Febrero' },
    { numero: 3, nombre: 'Marzo' },
    { numero: 4, nombre: 'Abril' },
    { numero: 5, nombre: 'Mayo' },
    { numero: 6, nombre: 'Junio' },
    { numero: 7, nombre: 'Julio' },
    { numero: 8, nombre: 'Agosto' },
    { numero: 9, nombre: 'Septiembre' },
    { numero: 10, nombre: 'Octubre' },
    { numero: 11, nombre: 'Noviembre' },
    { numero: 12, nombre: 'Diciembre' },
  ];

  mesSeleccionado: Mes;
  anioSeleccionado: number;

  usuariosVIP: Usuario[];
  usuariosNoVIP: Usuario[];

  buscarClientes(){
    this.usuarioService.getClientesNoVIP(this.mesSeleccionado.numero, this.anioSeleccionado).subscribe({
      next:(data)=>{
        this.usuariosNoVIP = data;
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
    this.usuarioService.getClientesVIP(this.mesSeleccionado.numero, this.anioSeleccionado).subscribe({
      next:(data)=>{
        this.usuariosVIP = data;
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

  onVolver(){
    this.router.navigate(['']);
  }
}
