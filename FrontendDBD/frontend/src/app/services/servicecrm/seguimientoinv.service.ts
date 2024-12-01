import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {SeguimientoInv} from '../../models/modelscrm/seguimientoinv.model';

@Injectable({
  providedIn: 'root'
})
export class SeguimientoinvService {
  private apiUrl = 'http://localhost:8080/mostrarseguimientoinv';

  constructor(private http: HttpClient) {}

  obtenerSeguimientoinv(id_cliente:string): Observable<SeguimientoInv> {
    return this.http.get<SeguimientoInv>(`${this.apiUrl}/${id_cliente}`);
  }
}
