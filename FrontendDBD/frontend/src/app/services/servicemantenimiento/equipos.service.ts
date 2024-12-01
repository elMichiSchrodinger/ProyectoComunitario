import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Equipo } from '../../models/modelsmantenimiento/equipo';
import { ListaEquipos } from '../../models/modelsmantenimiento/lista-equipos';
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
}
