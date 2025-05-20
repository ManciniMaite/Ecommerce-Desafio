import { Routes } from '@angular/router';
import { ListadoProductosComponent } from './listado-productos/listado-productos.component';
import { VerProductoComponent } from './ver-producto/ver-producto.component';
import { ConsultarClientesVipComponent } from './consultar-clientes-vip/consultar-clientes-vip.component';
import { ConsultarCarritoComponent } from './consultar-carrito/consultar-carrito.component';
import { CarritoGuard } from './guard/CarritoGuard';

export const routes: Routes = [
    { path: '', component: ListadoProductosComponent },
    { path:'ver-carrito/:idCarrito', component: ConsultarCarritoComponent, canActivate: [CarritoGuard]}, //si no hay carrito que redirija al inicio
    { path:'ver-producto/:idProducto', component: VerProductoComponent},
    { path:'consultar-clientes', component: ConsultarClientesVipComponent},
];
