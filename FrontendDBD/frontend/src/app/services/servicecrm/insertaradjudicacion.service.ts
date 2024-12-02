import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {InsertarAdjudicacion} from '../../models/modelscrm/insertaradjudicacion.model';
import {Observable} from 'rxjs';
import {MostrarPropuesta} from '../../models/modelscrm/mostrarpropuesta.model';

@Injectable({
  providedIn: 'root'
})
export class InsertaradjudicacionService {
  private apiUrl = 'http://localhost:8080/insertada';
  private apiUrl1 = 'http://localhost:8080/insertadr';
  private apiUrl2 = 'http://localhost:8080/actualizarpro';

  constructor(private http: HttpClient) {}

  aceptarAdjudicacion(adjudicacion: InsertarAdjudicacion): Observable<InsertarAdjudicacion> {
    return this.http.post<InsertarAdjudicacion>(this.apiUrl, adjudicacion);
  }
  rechazarAdjudicacion(adjudicacion: InsertarAdjudicacion): Observable<InsertarAdjudicacion> {
    return this.http.post<InsertarAdjudicacion>(this.apiUrl1, adjudicacion);
  }
  actualizarPropuesta(id_presentacion_propuesta:string): Observable<any> {
    const body = { id_presentacion_propuesta };
    return this.http.put(this.apiUrl2, body);
  }
}
