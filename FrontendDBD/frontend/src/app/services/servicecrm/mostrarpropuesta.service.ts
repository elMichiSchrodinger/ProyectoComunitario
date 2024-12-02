import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MostrarPropuesta} from '../../models/modelscrm/mostrarpropuesta.model';

@Injectable({
  providedIn: 'root'
})
export class MostrarpropuestaService {
  private apiUrl = 'http://localhost:8080/mostrarpro';
  id_presentacion_propuesta!: string;
  constructor(private http: HttpClient) {}

  obtenerPropuesta(id_cliente: string): Observable<MostrarPropuesta> {
    return this.http.get<MostrarPropuesta>(`${this.apiUrl}/${id_cliente}`);
  }
  setPropuesta(id_presentacion_propuesta:string){
    this.id_presentacion_propuesta=id_presentacion_propuesta;
  }
  getPropuesta(){
    return this.id_presentacion_propuesta;
  }
}
