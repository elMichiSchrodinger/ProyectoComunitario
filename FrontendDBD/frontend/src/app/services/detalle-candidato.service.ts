import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DetalleCandidato} from '../models/detalleCandidato.model';

@Injectable({
  providedIn: 'root'
})
export class DetalleCandidatoService {
  private apiUrl='http://localhost:8080/candidatos';
  aprobado=true;
  telefono=0;
  constructor(private http: HttpClient) { }
  obtenerCandidatosAprobados(): Observable<DetalleCandidato[]> {
    return this.http.get<DetalleCandidato[]>(`${this.apiUrl}/aprobados`);
  }
  obtenerCandidatosDesaprobados(): Observable<DetalleCandidato[]> {
    return this.http.get<DetalleCandidato[]>(`${this.apiUrl}/desaprobados`)
  }
  cambiarEstado(){
    this.aprobado=!this.aprobado;
  }
  getEstado(){
    return this.aprobado;
  }
  setTelefono(telefono:number){
    this.telefono = telefono;
  }
  getTelefono(){
    return this.telefono;
  }
}
