import { Component, HostListener, inject, Inject } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { HttpClientModule } from '@angular/common/http';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CarritoService } from './services/carrito.service';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, MatSnackBarModule,HttpClientModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  serviceCarritos = inject(CarritoService);
  router=inject(Router);

  public filtroUsuario = '';
  public filtroFecha = '';

  constructor(
    private snackBar: MatSnackBar) 
  {

  }

  onUsuarioChange(usuario: string) {
    this.filtroUsuario = usuario;
  }

  onFechaChange(fecha: string) {
    this.filtroFecha = fecha;
  }

  mostrarSnackbar(mensaje: string): void {
    this.snackBar.open(mensaje, 'Cerrar', {
      duration: 3000, 
      horizontalPosition: 'center',
      verticalPosition: 'bottom',
      panelClass: ['custom-snackbar']
    });
  }

  @HostListener('window:beforeunload', ['$event'])
  handleBeforeUnload(event: BeforeUnloadEvent): void {
    this.serviceCarritos.actualizarCarrito(null);
    const url = 'http://localhost:8080/Carrito/eliminar-todos'; 
    navigator.sendBeacon(url);
  }
}

@Component({
	selector: 'modal',
  standalone: true,
  imports: [
    MatProgressSpinnerModule,
    MatIconModule,
    MatDialogModule,
    MatButtonModule
  ],
	templateUrl: './dialogo.html',
	styleUrls: ['../styles.scss']
})
export class ModalDialog { 
	
	constructor(
	    public dialogRef: MatDialogRef<ModalDialog>,
	    	@Inject(MAT_DIALOG_DATA) public data: any) {
	}

	onNoClick(): void {
    if(this.data.onNoClick){
      this.data.onNoClick();
    }
		this.dialogRef.close();
	}
	onAceptClick(){
		this.dialogRef.close();
		this.data.onAceptClick();
	}
}
