import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {LoginCli} from '../models/logincli.model';

@Injectable({
  providedIn: 'root'
})
export class LogincliService {
  private apiUrl = 'http://localhost:8080/logcliente';
  private logincli!: LoginCli
  constructor(private http: HttpClient) {}
  obtenerLoginCli(ruc_dni: string, correo: string): Observable<LoginCli> {
    let params = new HttpParams()
    if (ruc_dni) params = params.set('ruc_dni',ruc_dni);
    if (correo) params = params.set('correo',correo);
    return this.http.get<LoginCli>(`${this.apiUrl}`, { params });
  }

  setLoginCli(logincli:LoginCli){
    this.logincli = logincli;
  }

  getidLoginCli(){
    return this.logincli.id_cliente;
  }
}
