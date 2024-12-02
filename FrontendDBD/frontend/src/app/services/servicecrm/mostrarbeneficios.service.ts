import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MostrarBeneficios} from '../../models/modelscrm/mostrarbeneficios';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MostrarbeneficiosService {
  private apiUrl = 'http://localhost:8080/mostrarbe';

  constructor(private http: HttpClient) {}

  obtenerBeneficios(id_presentacion_propuesta: string): Observable<MostrarBeneficios[]> {
    return this.http.get<MostrarBeneficios[]>(`${this.apiUrl}/${id_presentacion_propuesta}`);
  }
}
