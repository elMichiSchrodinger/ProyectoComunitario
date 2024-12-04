import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { lastValueFrom } from 'rxjs';


export interface GestionProyecto {
  idProyecto: string,
  nombreProyecto: string,
  fechaInicio: string,
  fechaFin: string,
  idEstado: number

}
@Injectable({
  providedIn: 'root'
})
export class ServiceProyecto {
  private apiUrl = 'http://localhost:8080/detalleProyectos'; // Cambia la URL base si es necesario

  constructor(private http: HttpClient) {}

  async listar() {
    return lastValueFrom(this.http.get<GestionProyecto[]>(this.apiUrl));
  }

  async obtenerPorId(id:string) {
    return lastValueFrom(this.http.get<GestionProyecto[]>(`${this.apiUrl}/${id}`));
  }

  async crear(data:GestionProyecto) {
    return lastValueFrom(this.http.post(this.apiUrl,data));
  }

  async actualizar(data:GestionProyecto) {
    return lastValueFrom(this.http.put(`${this.apiUrl}/${data.idProyecto}`, data));
  }

  async eliminar(id:string) {
    return lastValueFrom(this.http.delete(`${this.apiUrl}/${id}`));
  }
}
