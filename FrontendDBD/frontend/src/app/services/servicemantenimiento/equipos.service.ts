import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Equipo } from '../../models/modelsmantenimiento/equipo';
import { ListaEquipos } from '../../models/modelsmantenimiento/lista-equipos';
import { Falla } from '../../models/modelsmantenimiento/falla';
@Injectable({
  providedIn: 'root'
})
export class EquiposService {
  private apiUrl = 'http://localhost:8080/cmms/equipos';

  constructor(private http: HttpClient) { }

  obtenerEquipos(): Observable<ListaEquipos[]> {
    return this.http.get<ListaEquipos[]>(this.apiUrl);
  }
  obtenerEquipoPorId(id: string): Observable<Equipo> {
    return this.http.get<Equipo>(`${this.apiUrl}/${id}`);
  }

  agregarNuevoEquipo(equipo: Equipo): Observable<Equipo> {
    return this.http.post<Equipo>(this.apiUrl, equipo);
  }
  reportarFalla(falla: Falla, id: string): Observable<Falla> {
    return this.http.post<Falla>(`${this.apiUrl}/${id}/reportar-falla`, falla);
    console.log(falla);
    console.log(this.http);
  }
}
