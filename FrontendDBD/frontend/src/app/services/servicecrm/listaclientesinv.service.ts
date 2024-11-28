import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {MostrarListaInv} from '../../models/modelscrm/listaclientesinv.model';

@Injectable({
  providedIn: 'root'
})
export class ListaclientesinvService {
  private apiUrl = 'http://localhost:8080/mostrarlistinv';
  constructor(private http:HttpClient) { }
  mostrarlistainv(): Observable<MostrarListaInv[]> {
    return this.http.get<MostrarListaInv[]>(this.apiUrl);
  }


}
