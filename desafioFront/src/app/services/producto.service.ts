import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../model/Producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private baseUrl = 'http://localhost:8080/Producto';

  constructor(private http: HttpClient) {}

  obtenerTodos(): Observable<Producto[]> {
    return this.http.get<Producto[]>(this.baseUrl);
  }

  findById(id: number): Observable<Producto> {
    return this.http.get<Producto>(`${this.baseUrl}/${id}`);
  }

}
