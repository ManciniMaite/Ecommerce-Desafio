import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { CarritoCrearRq } from '../model/CarritoCrearRq';
import { Carrito } from '../model/Carrito';
import { CarritoAgregarProductoRq } from '../model/CarritoAgregarProductoRq';
import { Compra } from '../model/Compra';
import { CarritoEliminarProductoRq } from '../model/CarritoEliminarProductoRq';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {

  private baseUrl = 'http://localhost:8080/Carrito'; 

  private carritoSubject = new BehaviorSubject<Carrito | null>(null);
  carrito$ = this.carritoSubject.asObservable();

  constructor(private http: HttpClient) { }

  actualizarCarrito(carrito: Carrito) {
    this.carritoSubject.next(carrito);
  }

  obtenerCarritoActual(): Carrito | null {
    return this.carritoSubject.getValue();
  }

  crearCarrito(rq: CarritoCrearRq): Observable<Carrito> {
    return this.http.post<Carrito>(`${this.baseUrl}/crear`, rq);
  }

  agregarProducto(rq: CarritoAgregarProductoRq): Observable<Carrito> {
    return this.http.put<Carrito>(`${this.baseUrl}/agregar`, rq);
  }

  verCarrito(id: string): Observable<Carrito> {
    return this.http.get<Carrito>(`${this.baseUrl}/${id}`);
  }

  finalizarCarrito(idCarrito: string): Observable<Compra> {
    return this.http.post<Compra>(`${this.baseUrl}/finalizar/${idCarrito}`, null);
  }

  eliminarItemDelCarrito(rq: CarritoEliminarProductoRq): Observable<Carrito> {
    return this.http.delete<Carrito>(`${this.baseUrl}/eliminar-producto`, { body: rq });
  }

  eliminarCarrito(idCarrito: string): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/eliminar-carrito/${idCarrito}`);
  }

}
