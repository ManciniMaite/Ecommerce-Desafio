import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../model/Usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private baseUrl = 'http://localhost:8080/Clientes'; 

  constructor(private http: HttpClient) { }

  getClientesVIP(mes: number, anio: number): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.baseUrl}/usuarios-VIP/${mes}/${anio}`);
  }

  getClientesNoVIP(mes: number, anio: number): Observable<Usuario[]> {
    return this.http.get<Usuario[]>(`${this.baseUrl}/usuarios-No-VIP/${mes}/${anio}`);
  }
}
