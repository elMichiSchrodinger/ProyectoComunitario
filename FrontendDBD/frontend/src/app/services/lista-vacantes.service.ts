import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {VacanteResumidaModel} from '../models/vacanteResumida.model';
import {Observable} from 'rxjs';
import {Vacante} from '../models/vacante.model';

@Injectable({
  providedIn: 'root'
})
export class ListaVacantesService {
  private apiUrl='http://localhost:8080/vacantes';
  private vacante!:VacanteResumidaModel;
  constructor(private http: HttpClient) { }
  setVacante(vacante:VacanteResumidaModel){
    this.vacante = vacante;
  }
  getVacante(){
    return this.vacante;
  }
  obtenerVacantes(nombrePuesto?:string, area?:string):Observable<VacanteResumidaModel[]>{
    let params = new HttpParams();
    if (nombrePuesto) params = params.set('nombrePuesto',nombrePuesto);
    if (area) params = params.set('area',area);
    return this.http.get<VacanteResumidaModel[]>(this.apiUrl, { params });
  }
  obtenerVacante(vacante:VacanteResumidaModel):Observable<Vacante>{
    return this.http.get<Vacante>(`${this.apiUrl}/${vacante.id_vacante}`);
  }
}
