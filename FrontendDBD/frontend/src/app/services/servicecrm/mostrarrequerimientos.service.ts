import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {MostrarRequerimientos} from '../../models/modelscrm/mostrarrequerimientos.model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MostrarrequerimientosService {
  private apiUrl = 'http://localhost:8080/mostrarreq';

  constructor(private http: HttpClient) { }

  obtenerRequerimientos(id_invitacion: string): Observable<MostrarRequerimientos[]> {
    return this.http.get<MostrarRequerimientos[]>(`${this.apiUrl}/${id_invitacion}`);
  }
}
