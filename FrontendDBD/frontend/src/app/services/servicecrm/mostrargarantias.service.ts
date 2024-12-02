import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MostrarGarantias} from '../../models/modelscrm/mostrargarantias';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MostrargarantiasService {
  private apiUrl = 'http://localhost:8080/mostrarga';

  constructor(private http: HttpClient) {}

  obtenerGarantias(id_presentacion_propuesta: string): Observable<MostrarGarantias[]> {
    return this.http.get<MostrarGarantias[]>(`${this.apiUrl}/${id_presentacion_propuesta}`);
  }
}
