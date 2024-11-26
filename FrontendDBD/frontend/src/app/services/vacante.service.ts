import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Vacante} from '../models/vacante.model';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VacanteService {
  private apiUrl="http://localhost:8080/vacantes";
  constructor(private http: HttpClient) {}
  crearVacante(vacante:Vacante, idEmpleado:string):Observable<Vacante>{
    return this.http.post<Vacante>(`${this.apiUrl}/crear/${idEmpleado}`,vacante);
  }
}
